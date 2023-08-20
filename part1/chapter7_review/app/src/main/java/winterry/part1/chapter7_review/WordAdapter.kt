package winterry.part1.chapter7_review

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import winterry.part1.chapter7_review.databinding.ItemViewBinding

class WordAdapter(val wordList: MutableList<Word>,
                  private val itemClickListener: ItemClickListener,
                  ): RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    class WordViewHolder(val binding: ItemViewBinding): RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = ItemViewBinding.inflate(inflater, parent, false)

        return WordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word = wordList[position]
        holder.binding.apply {
            itemWordTextView.text = word.text
            itemMeanTextView.text = word.mean
            itemTypeChip.text = word.type
        }
        holder.itemView.setOnClickListener { itemClickListener?.onClick(word) }
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    interface ItemClickListener {
        fun onClick(word: Word)
    }
}