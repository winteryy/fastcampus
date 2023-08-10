package winterry.part1.chapter3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import winterry.part1.chapter3.databinding.ActivityMainBinding
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var cmToM = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val outputTextView = binding.outputTextView
        val outputUnitTextView = binding.outputUnitTextView
        val inputEditText = binding.inputEditText
        val inputUnitTextView = binding.inputUnitTextView
        val swapImgBtn = binding.swapImgBtn

        var inputNumber : BigDecimal = BigDecimal("0")


        inputEditText.addTextChangedListener { text ->
            inputNumber = if(text.isNullOrEmpty()){
                BigDecimal(0)
            } else {
                text.toString().toBigDecimal()
            }

            if(cmToM) {
                outputTextView.text = inputNumber.times(BigDecimal("0.01")).toString()
            } else {
                outputTextView.text = inputNumber.times(BigDecimal("100")).toString()
            }
        }

        swapImgBtn.setOnClickListener {
            cmToM = !cmToM
            if(cmToM){
                inputUnitTextView.text = "cm"
                outputUnitTextView.text = "m"
                outputTextView.text = inputNumber.times(BigDecimal("0.01")).toString()
            } else {
                inputUnitTextView.text = "m"
                outputUnitTextView.text = "cm"
                outputTextView.text = inputNumber.times(BigDecimal("100")).toString()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("cmToM", cmToM)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        cmToM = savedInstanceState.getBoolean("cmToM")
        binding.inputUnitTextView.text = if(cmToM) "cm" else "m"
        binding.outputUnitTextView.text = if(cmToM) "m" else "cm"
        super.onRestoreInstanceState(savedInstanceState)
    }
}