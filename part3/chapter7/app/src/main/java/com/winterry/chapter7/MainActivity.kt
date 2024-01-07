package com.winterry.chapter7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.winterry.chapter7.databinding.ActivityMainBinding
import com.winterry.chapter7.model.ContentEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()
    private val adapter by lazy { ListAdapter(Handler()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            view = this@MainActivity
            recyclerView.adapter = adapter
            val decoration = DividerItemDecoration(this@MainActivity, LinearLayout.VERTICAL)
            recyclerView.addItemDecoration(decoration)
        }

        lifecycleScope.launch {
            viewModel.contentList
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collectLatest {
                    binding.emptyTextView.isVisible = it.isEmpty()
                    binding.recyclerView.isVisible = it.isNotEmpty()
                    adapter.submitList(it)
                }
        }
    }

    fun onClickAdd() {
        InputActivity.start(this)
    }

    inner class Handler {
        fun onClickItem(item: ContentEntity) {
            InputActivity.start(this@MainActivity, item)
        }

        fun onLongClickItem(item: ContentEntity): Boolean {
            viewModel.deleteItem(item)
            Toast.makeText(this@MainActivity, "삭제 완료", Toast.LENGTH_SHORT).show()
            return false
        }

        fun onCheckedItem(item: ContentEntity, checked: Boolean) {
            viewModel.updateItem(item.copy(isDone = checked))
        }
    }
}