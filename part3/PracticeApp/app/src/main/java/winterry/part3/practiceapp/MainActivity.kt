package winterry.part3.practiceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.LinearLayoutManager
import winterry.part3.practiceapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CatListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CatListAdapter()
        binding.catRecyclerView.adapter = adapter
        binding.catRecyclerView.layoutManager = LinearLayoutManager(this)

        adapter.submitList(firstSetupData())
        Handler(Looper.getMainLooper()).postDelayed(
            { adapter.submitList(secondSetupData()) },
            3000
        )

    }

    private fun firstSetupData(): List<CatDataModel> {
        return listOf(
            CatDataModel(1, "cat1", 3),
            CatDataModel(2, "cat2", 1),
            CatDataModel(3, "cat3", 10),
            CatDataModel(4, "cat4", 11),
            CatDataModel(5, "cat5", 10)
        )
    }

    private fun secondSetupData(): List<CatDataModel> {
        return listOf(
            CatDataModel(3, "cat3", 10),
            CatDataModel(4, "cat4", 11),
            CatDataModel(5, "cat5", 10),
            CatDataModel(6, "cat6", 13),
            CatDataModel(7, "cat7", 16),
            CatDataModel(8, "cat8", 8)
        )
    }
}