package winterry.part1.chapter7

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import winterry.part1.chapter7.databinding.ItemWordBinding

class WordAdapter(private val list: MutableList<Word>,
                  private val itemClickListener: ItemClickListener? = null
                  ): RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = ItemWordBinding.inflate(inflater, parent, false)

        return WordViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word = list[position]
        holder.bind(word)
        holder.itemView.setOnClickListener { itemClickListener?.onClick(word) }
//            holder.binding.apply {
//            val word = list[position]
//            textTextView.text = word.text
//            meanTextView.text = word.mean
//            typeChip.text = word.type
//        }
    }

    class WordViewHolder(private val binding: ItemWordBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(word:Word) {
            binding.apply {
                textTextView.text = word.text
                meanTextView.text = word.mean
                typeChip.text = word.type
            }
        }
    }

    interface ItemClickListener {
        fun onClick(word: Word)
    }
}