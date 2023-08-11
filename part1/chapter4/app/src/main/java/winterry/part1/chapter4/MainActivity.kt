package winterry.part1.chapter4

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import winterry.part1.chapter4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(binding.root)

        binding.goInputActivityBtn.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            startActivity(intent)
        }

        binding.deleteBtn.setOnClickListener {
            deleteData()
        }

        binding.emergencyContactLayer.setOnClickListener {
            with(Intent(Intent.ACTION_VIEW)) {
                val phoneNumber = binding.emergencyContactValueTextView.text.toString()
                    .replace("-", "")
                data = Uri.parse("tel:$phoneNumber")
                startActivity(this)
            }
        }

    }

    override fun onResume() {
        super.onResume()

        getDataUiUpdate()
    }

    private fun getDataUiUpdate() {
        with(getSharedPreferences(USER_INFORMATION, MODE_PRIVATE)) {
            binding.nameValueTextView.text = getString(NAME, "미정")
            binding.birthdateValueTextView.text = getString(BIRTHDATE, "미정")
            binding.bloodTypeValueTextView.text = getString(BLOOD_TYPE, "미정")
            binding.emergencyContactValueTextView.text = getString(EMERGENCY_CONTACT, "미정")

            val warning = getString(WARNING, "미정")
            binding.warningValueTextView.isVisible = !warning.isNullOrEmpty()
            binding.warningTextView.isVisible = !warning.isNullOrEmpty()

            if(!warning.isNullOrEmpty()) {
                binding.warningValueTextView.text = warning
            }
        }
    }

    private fun deleteData() {
        with(getSharedPreferences(USER_INFORMATION, MODE_PRIVATE).edit()) {
            clear()
            apply()
            getDataUiUpdate()
        }


        Toast.makeText(this, "초기화를 완료했습니다.", Toast.LENGTH_SHORT).show()
    }
}