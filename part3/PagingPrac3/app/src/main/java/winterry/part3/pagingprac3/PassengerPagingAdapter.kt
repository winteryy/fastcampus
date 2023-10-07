package winterry.part3.pagingprac3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import winterry.part3.pagingprac3.data.Data
import winterry.part3.pagingprac3.databinding.ItemRvContentBinding
import winterry.part3.pagingprac3.new_data.NewItem

class PassengerPagingAdapter: PagingDataAdapter<NewItem, PassengerPagingAdapter.ViewHolder>(diffUtil) {

    class ViewHolder(private val binding: ItemRvContentBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NewItem) {
            binding.apply {
                itemTextView.text = item.id.toString()
                itemImageView.load(item.owner.avatarUrl)
            }
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.run {
            holder.bind(this)
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
        val diffUtil = object : DiffUtil.ItemCallback<NewItem>() {
            override fun areItemsTheSame(oldItem: NewItem, newItem: NewItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: NewItem, newItem: NewItem): Boolean {
                return oldItem == newItem
            }

        }
    }

}