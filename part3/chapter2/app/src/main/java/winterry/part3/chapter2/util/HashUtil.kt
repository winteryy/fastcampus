package winterry.part3.chapter2.util

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Base64
import android.util.Log
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.Arrays

object HashUtil {
    private const val LOG = "LOG"
    private const val HASH_TYPE = "SHA-256"
    private const val NUM_HASHED_BYTES = 9
    private const val NUM_BASE64_CHAR = 11

    fun getAppSignatures(context: Context): List<String> {
        val appCodes = mutableListOf<String>()

        try {
            val packageName = context.packageName
            val packageManager = context.packageManager
            val signatures = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES).signatures

            for (signature in signatures) {
                val hash = getHash(packageName, signature.toCharsString())

                if (hash != null) {
                    appCodes.add(String.format("%s", hash))
                }

                Log.d(LOG, String.format("이 값을 SMS 뒤에 써서 보내주면 됩니다 : %s", hash))
            }
        } catch (e: PackageManager.NameNotFoundException) {
            Log.d(LOG, "Unable to find package to obtain hash. : $e")
        }

        return appCodes
    }

    private fun getHash(packageName: String, signature: String): String? {
        val appInfo = "$packageName $signature"

        try {
            val messageDigest = MessageDigest.getInstance(HASH_TYPE)

            messageDigest.update(appInfo.toByteArray(StandardCharsets.UTF_8))


            val hashSignature = Arrays.copyOfRange(messageDigest.digest(), 0, NUM_HASHED_BYTES)
            val base64Hash = Base64
                .encodeToString(hashSignature, Base64.NO_PADDING or Base64.NO_WRAP)
                .substring(0, NUM_BASE64_CHAR)

            Log.d(LOG, String.format("\nPackage : %s\nHash : %s", packageName, base64Hash))

            return base64Hash

        } catch (e: NoSuchAlgorithmException) {
            Log.d(LOG, "hash:NoSuchAlgorithm : $e")
        }

        return null
    }
}