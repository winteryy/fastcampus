package winterry.part3.chapter6.viewholder

import winterry.part3.chapter6.ListAdapter
import winterry.part3.chapter6.databinding.ItemViewpagerBinding
import winterry.part3.chapter6.model.ListItem
import winterry.part3.chapter6.model.ViewPager

class ViewPagerViewHolder(
    binding: ItemViewpagerBinding
): BindingViewHolder<ItemViewpagerBinding>(binding) {

    private val adapter = ListAdapter()

    init {
        binding.viewPager.adapter = adapter
    }

    override fun bind(item: ListItem) {
        super.bind(item)
        item as ViewPager
        adapter.submitList(item.items)
    }
}