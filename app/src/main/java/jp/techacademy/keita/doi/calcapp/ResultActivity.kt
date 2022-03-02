package jp.techacademy.keita.doi.calcapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import jp.techacademy.keita.doi.calcapp.databinding.ActivityResultBinding

enum class ExtraKey(val key: String) {
    VALUE1("VALUE1"),
    VALUE2("VALUE2"),
    SYM("SYM")
}

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val value1 = intent.getIntExtra(ExtraKey.VALUE1.key, 0)
        val value2 = intent.getIntExtra(ExtraKey.VALUE2.key, 0)
        val sym = intent.getStringExtra(ExtraKey.SYM.key)

        when (sym) {
            "+" -> binding.textview.text = "${value1 + value2}"
            "-" -> binding.textview.text = "${value1 - value2}"
            "*" -> binding.textview.text = "${value1 * value2}"
            "/" -> binding.textview.text = if (value2 == 0) "error" else "${value1.toDouble() / value2.toDouble()}"
            else -> binding.textview.text = "${value1}${sym}${value2}"
        }
    }
}