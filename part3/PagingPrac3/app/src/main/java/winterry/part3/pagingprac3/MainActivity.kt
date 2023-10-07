package winterry.part3.pagingprac3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import winterry.part3.pagingprac3.databinding.ActivityMainBinding


//https://api.instantwebtools.net/v1/passenger?page=1&size=90

//-> 아래 주소로 변경

//https://api.github.com/search/repositories?q=android?page=1&per_page=90

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PassengerPagingAdapter

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = PassengerPagingAdapter()

        binding.run {
            rv.adapter = this@MainActivity.adapter
            rv.layoutManager = LinearLayoutManager(this@MainActivity)

            refreshButton.setOnClickListener {
                adapter.refresh()
            }
        }

        lifecycleScope.launch {
            viewModel.items.collect {
                adapter.submitData(it)
            }
        }

        lifecycleScope.launch {
            adapter.loadStateFlow.collect {
                val isLoadingNext = it.source.append is LoadState.Loading
                binding.loadingProgressBar.isVisible = isLoadingNext

                val isLoadingPrev = it.source.prepend is LoadState.Loading
                binding.prevLoadingProgressBar.isVisible = isLoadingPrev
            }
        }
    }
}