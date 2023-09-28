package winterry.part3.chapter5.list.viewholder

import androidx.recyclerview.widget.RecyclerView
import winterry.part3.chapter5.databinding.ItemVideoBinding
import winterry.part3.chapter5.model.ListItem
import winterry.part3.chapter5.model.VideoItem

class VideoViewHolder(
    private val binding: ItemVideoBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ListItem) {
        item as VideoItem
        binding.item = item
    }
}