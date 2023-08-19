package winterry.part1.chapter7_review

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.chip.Chip
import winterry.part1.chapter7_review.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        val types = listOf("명사", "대명사", "동사", "형용사", "부사", "전치사", "접속사", "감탄사")

        types.forEach { type ->
            binding.typeChipGroup.addView(createChip(type))
        }

    }

    private fun createChip(type : String): Chip {
        return Chip(this).apply {
            text = type
            isCheckable = true
            isClickable = true
        }
    }
}