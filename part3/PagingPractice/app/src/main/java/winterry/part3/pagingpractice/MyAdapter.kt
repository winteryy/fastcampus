package winterry.part3.pagingpractice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import winterry.part3.pagingpractice.databinding.ItemViewBinding

class MyAdapter: PagingDataAdapter<User, MyAdapter.ViewHolder>(diffUtil) {

    class ViewHolder(private val binding: ItemViewBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: User) {
            binding.apply {
                idTextView.text = item.id.toString()
                userNameTextView.text = item.userName
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

        }
    }
}