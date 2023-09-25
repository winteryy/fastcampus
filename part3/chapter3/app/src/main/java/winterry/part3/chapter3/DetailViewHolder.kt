package winterry.part3.chapter3

import androidx.recyclerview.widget.RecyclerView
import winterry.part3.chapter3.databinding.ItemDetailBinding
import winterry.part3.chapter3.model.DetailItem

class DetailViewHolder(private val binding: ItemDetailBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: DetailItem) {
        binding.item = item
    }
}