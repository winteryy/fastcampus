package winterry.part2.chapter10.ui.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import winterry.part2.chapter10.data.ArticleModel
import winterry.part2.chapter10.databinding.ItemArticleBinding

class BookmarkArticleAdapter(
    val onItemClicked: (ArticleModel) -> Unit
): ListAdapter<ArticleModel, BookmarkArticleAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemArticleBinding):
        RecyclerView.ViewHolder(binding.root) {

            fun bind(item: ArticleModel) {

                binding.bookmarkImageButton.isVisible = false

                binding.descriptionTextView.text = item.description

                Glide.with(binding.thumbnailImageView)
                    .load(item.imageUrl)
                    .into(binding.thumbnailImageView)

                binding.root.setOnClickListener {
                    onItemClicked(item)
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
        val diffUtil = object: DiffUtil.ItemCallback<ArticleModel>() {
            override fun areItemsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
                return oldItem.articleId == newItem.articleId
            }

            override fun areContentsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
                return oldItem == newItem
            }

        }
    }

}