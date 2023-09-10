package winterry.part2.chapter9

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.model.User
import winterry.part2.chapter9.databinding.ActivityLoginBinding

class LoginActivity: AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var emailLoginResult: ActivityResultLauncher<Intent>
    private lateinit var pendingUser: User

    private val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            showErrorToast()
            error.printStackTrace()
        } else if (token != null) {
            getKakaoAccountInfo()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        KakaoSdk.init(this, "0ed885e40738b40d5910397455ed4495")

        if (AuthApiClient.instance.hasToken()) {
            UserApiClient.instance.accessTokenInfo { tokenInfo, error ->  
                if (error == null) {
                    getKakaoAccountInfo()
                }
            }
        }

        emailLoginResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode== RESULT_OK) {
                val email = it.data?.getStringExtra("email")

                if (email == null) {
                    showErrorToast()
                    return@registerForActivityResult
                } else {
                    signInFirebase(pendingUser, email)
                }
            }
        }


        binding.kakaoTalkLoginButton.setOnClickListener {
            if(UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                    if (error != null) {
                        if(error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }
                        UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                    } else if (token != null) {
                        if (Firebase.auth.currentUser == null) {
                            getKakaoAccountInfo()
                        } else {
                            navigateToMapActivity()
                        }
                        Log.e("LoginActivity", "token == $token")
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }

    }

    private fun showErrorToast() {
        Toast.makeText(this, "사용자 로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
    }

    private fun getKakaoAccountInfo() {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                showErrorToast()
                error.printStackTrace()
            } else if (user != null) {
                Log.e(
                    "LoginActivity",
                    "user; 회원번호: ${user.id} / 이메일: ${user.kakaoAccount?.email} / 닉네임: ${user.kakaoAccount?.profile?.nickname} / 프로필 사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}"
                )

                checkKakaoUserData(user)
            }
        }
    }

    private fun checkKakaoUserData(user: User) {
        val kakaoEmail = user.kakaoAccount?.email.orEmpty()

        if(kakaoEmail.isEmpty()) {
            //이메일 받기

            pendingUser = user

            emailLoginResult.launch(Intent(this, EmailLoginActivity::class.java))
            return
        }

        signInFirebase(user, kakaoEmail)
    }

    private fun signInFirebase(user: User, email: String) {
        val uid = user.id.toString()

        Firebase.auth.createUserWithEmailAndPassword(email, uid).addOnCompleteListener {
            if (it.isSuccessful) {
                updateFirebaseDatabase(user)
            }
        }.addOnFailureListener {
            if(it is FirebaseAuthUserCollisionException) {
                Firebase.auth.signInWithEmailAndPassword(email, uid).addOnCompleteListener { result ->
                    if (result.isSuccessful) {
                        updateFirebaseDatabase(user)
                    } else {
                        showErrorToast()
                    }
                }.addOnFailureListener { error ->
                    error.printStackTrace()
                    showErrorToast()
                }
            } else {
                showErrorToast()
            }
        }

    }

    private fun updateFirebaseDatabase(user: User) {
        val uid = Firebase.auth.currentUser?.uid.orEmpty()

        val personMap = mutableMapOf<String, Any>()
        personMap["uid"] = uid
        personMap["name"] = user.kakaoAccount?.profile?.nickname.orEmpty()
        personMap["profilePhoto"] = user.kakaoAccount?.profile?.thumbnailImageUrl.orEmpty()

        Firebase.database.reference.child("Person").child(uid).updateChildren(personMap)

        navigateToMapActivity()

    }

    private fun navigateToMapActivity() {
        startActivity(Intent(this, MapActivity::class.java))
    }
}