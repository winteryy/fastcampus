package kr.co.fastcampus.part4plus.chapter2.ui.home

import android.content.Intent
import kr.co.fastcampus.part4plus.chapter2.ui.content.ContentActivity

class HomeState(val activity: HomeActivity) {
    fun showContent(index: Int) {
        activity.startActivity(Intent(activity, ContentActivity::class.java).apply {
            putExtra("id", index)
        })
    }
}
