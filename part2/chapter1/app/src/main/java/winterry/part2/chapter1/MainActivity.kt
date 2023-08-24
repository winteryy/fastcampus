package winterry.part2.chapter1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import com.google.android.material.tabs.TabLayoutMediator
import winterry.part2.chapter1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnTabLayoutChanged {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreference =
            getSharedPreferences(WebViewFragment.SHARED_PREFERENCE, Context.MODE_PRIVATE)
        val tab0 = sharedPreference.getString("tab0_name", "월요웹툰")
        val tab1 = sharedPreference.getString("tab1_name", "화요웹툰")
        val tab2 = sharedPreference.getString("tab2_name", "수요웹툰")

        binding.viewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            run {
//                val textView = TextView(this@MainActivity)
//                textView.text = when(position) {
//                    0 -> tab0
//                    1 -> tab1
//                    else -> tab2
//                }
//                textView.gravity = Gravity.CENTER
//                tab.customView = textView
                tab.text = when(position) {
                    0 -> tab0
                    1 -> tab1
                    else -> tab2
                }
            }
        }.attach()
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.fragments[binding.viewPager.currentItem]
        if (currentFragment is WebViewFragment) {
            if (currentFragment.canGoBack()) {
                currentFragment.goBack()
            } else {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
    }

    override fun nameChanged(position: Int, name: String) {
        val tab = binding.tabLayout.getTabAt(position)
        tab?.text = name
    }
}