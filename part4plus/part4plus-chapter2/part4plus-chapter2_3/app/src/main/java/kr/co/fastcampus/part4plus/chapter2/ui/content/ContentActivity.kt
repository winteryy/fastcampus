package kr.co.fastcampus.part4plus.chapter2.ui.content

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class ContentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContentScreen(intent.getIntExtra("id",0))
        }
    }
}
