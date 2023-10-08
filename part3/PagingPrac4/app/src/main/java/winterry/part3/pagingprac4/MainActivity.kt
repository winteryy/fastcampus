package winterry.part3.pagingprac4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import winterry.part3.pagingprac4.databinding.ActivityMainBinding
import winterry.part3.pagingprac4.state.GithubLoadStateAdapter


// https://api.github.com/search/repositories?q=android?page=1&per_page=90

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: GithubAdapter
    private lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = GithubAdapter()
        val str = intent.getStringExtra("inputText").toString()
        loadData(str)

//        binding.rv.apply {
//            adapter = this@MainActivity.adapter
//            layoutManager = LinearLayoutManager(this@MainActivity)
//        }
//
//        lifecycleScope.launch {
//            viewModel.items.collect {
//                adapter.submitData(it)
//            }
//        }


//        val str = intent.getStringExtra("inputText").toString()
//        Log.d("MainActivity", str)
    }

    fun loadData(str: String) {
        viewModelFactory = MainViewModelFactory(str)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        binding.rv.apply {
            adapter = this@MainActivity.adapter.withLoadStateFooter(
                GithubLoadStateAdapter {
                    this@MainActivity.adapter.retry()
                }
            )
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        lifecycleScope.launch {
            viewModel.items.collect {
                adapter.submitData(it)
            }
        }

    }
}