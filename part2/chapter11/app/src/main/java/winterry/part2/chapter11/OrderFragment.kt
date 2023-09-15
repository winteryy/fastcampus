package winterry.part2.chapter11

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import winterry.part2.chapter11.databinding.FragmentOrderBinding

class OrderFragment: Fragment(R.layout.fragment_order) {

    private lateinit var binding: FragmentOrderBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOrderBinding.bind(view)
    }
}