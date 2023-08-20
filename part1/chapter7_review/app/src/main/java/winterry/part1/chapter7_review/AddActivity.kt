package winterry.part1.chapter7_review

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.children
import androidx.core.widget.addTextChangedListener
import com.google.android.material.chip.Chip
import winterry.part1.chapter7_review.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    private var originWord: Word? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

        binding.wordAddBtn.setOnClickListener {
            if(checkValidInput()) {
                if(originWord==null) {
                    addWord()
                } else {
                    editWord()
                }
            }
        }
    }

    private fun initView() {
        val types = listOf("명사", "대명사", "동사", "형용사", "부사", "전치사", "접속사", "감탄사")

        types.forEach { type ->
            binding.typeChipGroup.addView(createChip(type))
        }

        binding.wordTextInputEditText.addTextChangedListener {
            it?.let { text ->
                binding.wordTextInputLayout.error = when(text.length) {
                    0 -> "값을 입력해주세요"
                    1 -> "2자 이상 입력해주세요"
                    else -> null
                }
            }
        }

        originWord = intent.getParcelableExtra<Word>("originWord")
        originWord?.let { word ->
            binding.wordTextInputEditText.setText(word.text)
            binding.meanTextInputEditText.setText(word.mean)
            (binding.typeChipGroup.children.firstOrNull { (it as Chip).text == word.type } as? Chip)?.isChecked = true
        }

    }

    private fun createChip(type : String): Chip {
        return Chip(this).apply {
            text = type
            isCheckable = true
            isClickable = true
        }
    }

    private fun addWord() {
        val text = binding.wordTextInputEditText.text.toString()
        val mean = binding.meanTextInputEditText.text.toString()
        val type = findViewById<Chip>(binding.typeChipGroup.checkedChipId).text.toString()

        val newWord = Word(text, mean, type)
        Thread {
            AppDatabase.getInstance(this)?.wordDao()?.insert(newWord)
            runOnUiThread { Toast.makeText(this, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show() }
            val intent = Intent().putExtra("isUpdated", true)
            setResult(RESULT_OK, intent)
            finish()
        }.start()
    }

    private fun editWord() {
        val text = binding.wordTextInputEditText.text.toString()
        val mean = binding.meanTextInputEditText.text.toString()
        val type = findViewById<Chip>(binding.typeChipGroup.checkedChipId).text.toString()

        val editWord = originWord?.copy(text = text, mean = mean, type = type)

        Thread {
            editWord?.let { word ->
                AppDatabase.getInstance(this)?.wordDao()?.update(word)
                val intent = Intent().putExtra("editWord", editWord)
                setResult(RESULT_OK, intent)
                runOnUiThread { Toast.makeText(this, "수정이 완료되었습니다.", Toast.LENGTH_SHORT).show() }
                finish()
            }
        }.start()
    }

    private fun checkValidInput(): Boolean {
        val text = binding.wordTextInputEditText.text
        val typeChecked = binding.typeChipGroup.checkedChipId
        return if (text.isNullOrEmpty() || text.length < 2 || binding.meanTextInputEditText.text.isNullOrEmpty() || typeChecked == View.NO_ID ) {
            Toast.makeText(this, "올바르지 않은 입력입니다", Toast.LENGTH_SHORT).show()
            false
        }else{
            true
        }
    }
}