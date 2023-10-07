package winterry.part3.pagingprac4

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import winterry.part3.pagingprac4.model.Item

class GithubAdapter: PagingDataAdapter<Item, GithubViewHolder>(DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder {
        return GithubViewHolder.create(parent)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }

        }
    }
}