package winterry.part1.chapter2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numberTextView = findViewById<TextView>(R.id.numberTextView)
        val resetBtn = findViewById<Button>(R.id.resetBtn)
        val plusBtn = findViewById<Button>(R.id.plusBtn)

        var number = 0

        resetBtn.setOnClickListener {
            number = 0
            numberTextView.text = number.toString()
        }

        plusBtn.setOnClickListener {
            number++
            numberTextView.text = number.toString()
        }
    }
}