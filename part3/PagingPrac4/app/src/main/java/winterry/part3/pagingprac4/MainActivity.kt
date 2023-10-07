package winterry.part3.pagingprac4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import winterry.part3.pagingprac4.databinding.ActivityMainBinding


// https://api.github.com/search/repositories?q=android?page=1&per_page=90

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: GithubAdapter

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = GithubAdapter()
        binding.rv.apply {
            adapter = this@MainActivity.adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        lifecycleScope.launch {
            viewModel.items.collect {
                adapter.submitData(it)
            }
        }


//        val str = intent.getStringExtra("inputText").toString()
//        Log.d("MainActivity", str)
    }
}