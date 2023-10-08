package winterry.part3.pagingprac4

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import winterry.part3.pagingprac4.databinding.ItemGithubBinding
import winterry.part3.pagingprac4.model.Item

class GithubViewHolder(private val binding: ItemGithubBinding): RecyclerView.ViewHolder(binding.root) {

    private var item: Item? = null

    init {
        binding.root.setOnClickListener {
            item?.url.let {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                binding.root.context.startActivity(intent)
            }
        }
    }

    fun bind(item: Item) {
        binding.nameTextView.text = item.name
        binding.urlTextView.text = item.url

        this.item = item
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