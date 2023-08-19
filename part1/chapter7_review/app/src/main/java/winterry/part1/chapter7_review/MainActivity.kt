package winterry.part1.chapter7_review

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import winterry.part1.chapter7_review.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), WordAdapter.ItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var wordAdapter: WordAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRV()

        binding.addBtn.setOnClickListener {
            Intent(this, AddActivity::class.java).let {
                startActivity(it)
            }
        }
    }

    private fun initRV() {
        val sampleList = mutableListOf(
            Word("단어1", "뜻1", "품사"),
            Word("단어2", "뜻2", "품사"),
            Word("단어3", "뜻3", "품사"),
            Word("단어4", "뜻4", "품사"),
            Word("단어5", "뜻5", "품사")
        )

        wordAdapter = WordAdapter(sampleList, this)
        binding.mainRV.apply {
            adapter = wordAdapter
            layoutManager = LinearLayoutManager(applicationContext)
            val dividerItemDecoration = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }

    }

    override fun onClick(word: Word){
        Toast.makeText(this, "${word.text}가 클릭되었습니다.", Toast.LENGTH_SHORT).show()
    }
}