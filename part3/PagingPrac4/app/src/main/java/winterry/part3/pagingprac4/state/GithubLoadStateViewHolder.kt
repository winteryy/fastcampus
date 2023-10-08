package winterry.part3.pagingprac4.state

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import winterry.part3.pagingprac4.databinding.ItemLoadStateBinding

class GithubLoadStateViewHolder(
    private val binding: ItemLoadStateBinding,
    private val retry: () -> Unit
): RecyclerView.ViewHolder(binding.root) {

    fun bind(loadState: LoadState) {
        binding.retryButton.isVisible = loadState is LoadState.Error
        binding.errorTextView.isVisible = loadState is LoadState.Error
        binding.loadingTextView.isVisible = loadState is LoadState.Loading

        binding.retryButton.setOnClickListener {
            retry.invoke()
        }
    }

}