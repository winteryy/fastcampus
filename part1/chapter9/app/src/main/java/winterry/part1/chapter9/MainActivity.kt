package winterry.part1.chapter9

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import winterry.part1.chapter9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.playBtn.setOnClickListener {
            checkPermission()
        }
        binding.pauseBtn.setOnClickListener {
            mediaPause()
        }
        binding.stopBtn.setOnClickListener {
            mediaStop()
        }
    }

    override fun onDestroy() {
        stopService(Intent(this, MediaPlayerService::class.java))
        super.onDestroy()
    }

    private fun checkPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED -> {
                Log.d("확인점1", "ㅇㅇ")
                mediaPlay()
            }
            shouldShowRequestPermissionRationale(
                Manifest.permission.POST_NOTIFICATIONS
            ) -> {
                Log.d("확인점3", "ㅇㅇ")
                showPermissionInfoDialog()
            }
            else -> {
                Log.d("확인점2", "ㅇㅇ")
                requestNotificationPost()
            }
        }

    }

    private fun showPermissionInfoDialog() {
        AlertDialog.Builder(this).apply {
            setMessage("음악 재생을 위해, 알림 생성 권한이 필요합니다.")
            setNegativeButton("취소", null)
            setPositiveButton("동의") { _, _ ->
                requestNotificationPost()
            }
        }.show()
    }

    private fun requestNotificationPost() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.POST_NOTIFICATIONS),
            100
        )
    }

    private fun mediaPlay() {
        val intent = Intent(this, MediaPlayerService::class.java).apply {
            action = MEDIA_PLAYER_PLAY
        }
        startService(intent)
    }

    private fun mediaPause() {
        val intent = Intent(this, MediaPlayerService::class.java).apply {
            action = MEDIA_PLAYER_PAUSE
        }
        startService(intent)
    }

    private fun mediaStop() {
        val intent = Intent(this, MediaPlayerService::class.java).apply {
            action = MEDIA_PLAYER_STOP
        }
        startService(intent)
    }
}