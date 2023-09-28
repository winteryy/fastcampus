package winterry.part3.chapter5.list.viewholder

import androidx.recyclerview.widget.RecyclerView
import winterry.part3.chapter5.databinding.ItemImageBinding
import winterry.part3.chapter5.model.ImageItem
import winterry.part3.chapter5.model.ListItem

class ImageViewHolder(
    private val binding: ItemImageBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ListItem) {
        item as ImageItem
        binding.item = item
    }
}