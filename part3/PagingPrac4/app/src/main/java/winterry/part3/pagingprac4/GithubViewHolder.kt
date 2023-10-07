package winterry.part3.pagingprac4

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import winterry.part3.pagingprac4.databinding.ItemGithubBinding
import winterry.part3.pagingprac4.model.Item

class GithubViewHolder(private val binding: ItemGithubBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Item) {
        binding.nameTextView.text = item.name
        binding.urlTextView.text = item.url
    }

    companion object {
        fun create(parent: ViewGroup): GithubViewHolder {

            return GithubViewHolder(ItemGithubBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))

        }
    }
}