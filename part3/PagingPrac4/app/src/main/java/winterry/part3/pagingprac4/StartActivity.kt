package winterry.part3.pagingprac4

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewmodel.CreationExtras
import winterry.part3.pagingprac4.databinding.ActivityStartBinding

class StartActivity: AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.inputButton.setOnClickListener {
            Intent(this, MainActivity::class.java).apply {
                putExtra("inputText", binding.inputEditText.text.toString())
                startActivity(this)
            }
        }
    }
}