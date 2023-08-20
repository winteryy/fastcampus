package winterry.part1.chapter7_review

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import winterry.part1.chapter7_review.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), WordAdapter.ItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var wordAdapter: WordAdapter
    private var selectedWord: Word? = null

    private val addWordResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val isUpdated = result?.data?.getBooleanExtra("isUpdated", false)

        if(result.resultCode == RESULT_OK && isUpdated == true) {
            updateAddWord()
        }
    }

    private val editWordResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val editWord = result?.data?.getParcelableExtra<Word>("editWord")

        if(result.resultCode == RESULT_OK && editWord != null) {
            updateEditWord(editWord)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRV()

        binding.addBtn.setOnClickListener {
            Intent(this, AddActivity::class.java).let {
                addWordResult.launch(it)
            }
        }

        binding.deleteImageView.setOnClickListener {
            deleteWord()
        }

        binding.editImageView.setOnClickListener {
            editWord()
        }
    }

    private fun initRV() {
        wordAdapter = WordAdapter(mutableListOf(), this)

        binding.mainRV.apply {
            adapter = wordAdapter
            layoutManager = LinearLayoutManager(applicationContext)
            val dividerItemDecoration = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }

        Thread{
            val wordList = AppDatabase.getInstance(this)?.wordDao()?.getAll() ?: emptyList()
            wordAdapter.wordList.addAll(wordList)
            runOnUiThread {
                wordAdapter.notifyDataSetChanged()
            }
        }.start()

    }

    private fun updateAddWord() {
        Thread {
            val addWord = AppDatabase.getInstance(this)?.wordDao()?.getLatestWord()?.let { word ->
                wordAdapter.wordList.add(0, word)
            }
            runOnUiThread {
                wordAdapter.notifyDataSetChanged()
            }
        }.start()
    }

    private fun updateEditWord(word: Word) {
        val index = wordAdapter.wordList.indexOfLast { it.id == word.id }
        wordAdapter.wordList[index] = word
        runOnUiThread {
            selectedWord = word
            wordAdapter.notifyItemChanged(index)
            binding.wordTextView.text = word.text
            binding.meanTextView.text = word.mean
        }
    }

    private fun deleteWord() {
        if(selectedWord == null) return

        Thread {
            selectedWord?.let { word ->
                AppDatabase.getInstance(this)?.wordDao()?.delete(word)
                runOnUiThread {
                    binding.wordTextView.text = ""
                    binding.meanTextView.text = ""
                    wordAdapter.wordList.remove(word)
                    wordAdapter.notifyDataSetChanged()
                    Toast.makeText(this, "삭제가 완료되었습니다.", Toast.LENGTH_SHORT).show()
                }
                selectedWord = null
            }
        }.start()
    }

    private fun editWord() {
        if(selectedWord == null) return

        Intent(this, AddActivity::class.java).putExtra("originWord", selectedWord).let {
            editWordResult.launch(it)
        }
    }

    override fun onClick(word: Word){
        Toast.makeText(this, "${word.text}가 클릭되었습니다.", Toast.LENGTH_SHORT).show()
        selectedWord = word
        binding.wordTextView.text = word.text
        binding.meanTextView.text = word.mean
    }
}