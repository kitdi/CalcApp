package jp.techacademy.keita.doi.calcapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import jp.techacademy.keita.doi.calcapp.databinding.ActivityResultBinding

enum class ExtraKey(val key: String) {
    VALUE1("VALUE1"),
    VALUE2("VALUE2"),
    SYM("SYM")
}

enum class CalcSymbol(val key: String) {
    PLUS("plus"),
    MINUS("minus"),
    TIMES("times"),
    BY("by")
}

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val value1 = intent.getDoubleExtra(ExtraKey.VALUE1.key, 0.0)
        val value2 = intent.getDoubleExtra(ExtraKey.VALUE2.key, 0.0)
        val sym = intent.getStringExtra(ExtraKey.SYM.key)

        when (sym) {
            CalcSymbol.PLUS.key -> binding.textview.text = "${value1 + value2}"
            CalcSymbol.MINUS.key -> binding.textview.text = "${value1 - value2}"
            CalcSymbol.TIMES.key -> binding.textview.text = "${value1 * value2}"
            CalcSymbol.BY.key -> {
                binding.textview.text = value2.takeUnless { it == 0.0 }.let {
                    if (it == null) "error" else "${value1 / value2}"
                }
            }
            else -> binding.textview.text = "${value1}${sym}${value2}"
        }
    }
}