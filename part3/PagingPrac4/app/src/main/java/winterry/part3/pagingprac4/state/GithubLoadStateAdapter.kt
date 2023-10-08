package winterry.part3.pagingprac4.state

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import winterry.part3.pagingprac4.databinding.ItemLoadStateBinding

class GithubLoadStateAdapter(
    private val retry: () -> Unit,
) : LoadStateAdapter<GithubLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: GithubLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): GithubLoadStateViewHolder {
        return GithubLoadStateViewHolder(
            ItemLoadStateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ) {
            retry.invoke()
        }
    }
}