package winterry.part3.chapter5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import com.google.android.material.tabs.TabLayoutMediator
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import winterry.part3.chapter5.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val searchFragment = SearchFragment()
    private val fragmentList = listOf(searchFragment, FavoriteFragment())
    private val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle, fragmentList)

    private var observableTextQuery: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()

    }

    private fun initView() {
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            view = this@MainActivity
            viewPager.adapter = adapter

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = if(fragmentList[position] is SearchFragment) {
                    "검색 결과"
                } else {
                    "즐겨 찾기"
                }
            }.attach()
        }
    }

    override fun onDestroy() {
        observableTextQuery?.dispose()
        observableTextQuery = null
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        observableTextQuery = Observable.create { emitter: ObservableEmitter<String>? ->
            (menu.findItem(R.id.search).actionView as SearchView).apply {
                setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String): Boolean {
                        emitter?.onNext(query)
                        return false
                    }

                    override fun onQueryTextChange(newText: String): Boolean {
                        binding.viewPager.setCurrentItem(0, true)
                        emitter?.onNext(newText)
                        return false
                    }

                })
            }
        }
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                searchFragment.searchKeyword(it)
            }

        return true
    }
}