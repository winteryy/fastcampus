package winterry.part2.chapter8

import android.app.Application
import android.content.Context

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        MyApplication.applicationContext = applicationContext
    }

    companion object {
        lateinit var applicationContext: Context
        const val CORRECT_VAL = 10_000_000
    }
}