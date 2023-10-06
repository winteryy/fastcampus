package winterry.part3.practiceapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import winterry.part3.practiceapp.databinding.ItemCatBinding

class CatListAdapter: ListAdapter<CatDataModel, CatListAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemCatBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CatDataModel) {
            binding.apply {
                catIdTextView.text = item.catId.toString()
                catAgeTextView.text = item.catAge.toString()
                catNameTextView.text = item.catName
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCatBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<CatDataModel>() {
            override fun areItemsTheSame(oldItem: CatDataModel, newItem: CatDataModel): Boolean {
                return oldItem.catId == newItem.catId
            }

            override fun areContentsTheSame(oldItem: CatDataModel, newItem: CatDataModel): Boolean {
                return oldItem == newItem
            }

        }
    }
}