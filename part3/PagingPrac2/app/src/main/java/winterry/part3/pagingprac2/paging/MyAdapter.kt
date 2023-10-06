package winterry.part3.pagingprac2.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import winterry.part3.pagingprac2.data.GithubResponseItem
import winterry.part3.pagingprac2.databinding.ItemRvContentBinding

class MyAdapter: PagingDataAdapter<GithubResponseItem, MyAdapter.ViewHolder>(diffUtil) {

    class ViewHolder(private val binding: ItemRvContentBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GithubResponseItem) {
            binding.nameTextView.text = item.name
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRvContentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<GithubResponseItem>() {
            override fun areItemsTheSame(
                oldItem: GithubResponseItem, newItem: GithubResponseItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: GithubResponseItem, newItem: GithubResponseItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

}