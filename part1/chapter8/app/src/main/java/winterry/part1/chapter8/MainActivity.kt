package winterry.part1.chapter8

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import winterry.part1.chapter8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var imageAdapter: ImageAdapter

    private val imageLoadLauncher = registerForActivityResult(
        ActivityResultContracts.GetMultipleContents() ) { uriList ->
            if(uriList.isNotEmpty()) updateImages(uriList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.apply {
            title = "사진 가져오기"
            setSupportActionBar(this)
        }


        binding.loadImgBtn.setOnClickListener {
            checkPermission()
        }
        binding.navigateFrameActivityBtn.setOnClickListener {
            navigateToFrameActivity()
        }

        initRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_add -> {
                checkPermission()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initRecyclerView() {
        imageAdapter = ImageAdapter(object : ImageAdapter.ItemClickListener{
            override fun onLoadMoreClick() {
                checkPermission()
            }
        })

        binding.imgRV.apply {
            adapter = imageAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    private fun navigateToFrameActivity() {
        val images = imageAdapter.currentList.filterIsInstance<ImageItems.Image>().map { it.uri.toString() }.toTypedArray()
        val intent = Intent(this, FrameActivity::class.java)
            .putExtra("images", images)
        startActivity(intent)
    }

    private fun checkPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_MEDIA_IMAGES
            ) == PackageManager.PERMISSION_GRANTED -> {
                loadImage()
            }
            shouldShowRequestPermissionRationale(
                Manifest.permission.READ_MEDIA_IMAGES
            ) -> {
                showPermissionInfoDialog()
            }
            else -> {
                requestReadMediaImage()
            }

        }
    }

    private fun showPermissionInfoDialog() {
        AlertDialog.Builder(this).apply {
            setMessage("이미지를 가져오기 위해서, 외부 이미지 읽기 권한이 필요합니다.")
            setNegativeButton("취소", null)
            setPositiveButton("동의") { _, _ ->
                requestReadMediaImage()
            }
        }.show()
    }

    private fun loadImage() {
        imageLoadLauncher.launch("image/*")
    }

    private fun requestReadMediaImage() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.READ_MEDIA_IMAGES),
            REQUEST_READ_MEDIA_IMAGES
        )
    }

    private fun updateImages(uriList: List<Uri>) {
        Log.i("updateImages", "$uriList")
        val images = uriList.map { ImageItems.Image(it) }
        val updatedImages = imageAdapter.currentList.toMutableList().apply {
            addAll(images)
        }
        imageAdapter.submitList(updatedImages)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode) {
            REQUEST_READ_MEDIA_IMAGES -> {
                val resultCode = grantResults.firstOrNull() ?: PackageManager.PERMISSION_DENIED
                if(resultCode == PackageManager.PERMISSION_GRANTED) {
                    loadImage()
                }
            }
        }
    }

    companion object {
        const val REQUEST_READ_MEDIA_IMAGES = 100
    }
}