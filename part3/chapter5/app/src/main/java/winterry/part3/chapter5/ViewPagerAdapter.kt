package winterry.part3.chapter5

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifeCycle: Lifecycle,
    private val list: List<Fragment>
): FragmentStateAdapter(fragmentManager, lifeCycle) {
    override fun getItemCount() = list.size

    override fun createFragment(position: Int) = list[position % itemCount]

}