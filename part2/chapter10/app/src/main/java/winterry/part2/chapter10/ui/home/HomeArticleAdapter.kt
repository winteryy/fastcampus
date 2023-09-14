package winterry.part2.chapter10.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import winterry.part2.chapter10.R
import winterry.part2.chapter10.data.ArticleModel
import winterry.part2.chapter10.databinding.ItemArticleBinding

class HomeArticleAdapter(
    val onItemClicked: (ArticleItem) -> Unit,
    val onBookmarkClicked: (String, Boolean) -> Unit
) : ListAdapter<ArticleItem, HomeArticleAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ArticleItem) {

            binding.descriptionTextView.text = item.description

            Glide.with(binding.thumbnailImageView)
                .load(item.imageUrl)
                .into(binding.thumbnailImageView)

            binding.root.setOnClickListener {
                onItemClicked(item)
            }

            if (item.isBookmark) {
                binding.bookmarkImageButton.setBackgroundResource(R.drawable.baseline_bookmark_24)
            } else {
                binding.bookmarkImageButton.setBackgroundResource(R.drawable.baseline_bookmark_border_24)
            }

            binding.bookmarkImageButton.setOnClickListener {
                onBookmarkClicked.invoke(item.articleId, item.isBookmark.not())

                item.isBookmark = item.isBookmark.not()
                if (item.isBookmark) {
                    binding.bookmarkImageButton.setBackgroundResource(R.drawable.baseline_bookmark_24)
                } else {
                    binding.bookmarkImageButton.setBackgroundResource(R.drawable.baseline_bookmark_border_24)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ArticleItem>() {
            override fun areItemsTheSame(oldItem: ArticleItem, newItem: ArticleItem): Boolean {
                return oldItem.articleId == newItem.articleId
            }

            override fun areContentsTheSame(oldItem: ArticleItem, newItem: ArticleItem): Boolean {
                return oldItem == newItem
            }

        }
    }

}