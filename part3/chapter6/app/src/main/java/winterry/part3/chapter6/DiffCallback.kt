package winterry.part3.chapter6

import androidx.recyclerview.widget.DiffUtil
import winterry.part3.chapter6.model.ListItem

class DiffCallback<T: ListItem>: DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.viewType == newItem.viewType && oldItem.getKey() == newItem.getKey()
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}