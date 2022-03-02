package jp.techacademy.keita.doi.calcapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import jp.techacademy.keita.doi.calcapp.databinding.ActivityMainBinding

val LOG_TAG = "CalcApp"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonPlus.setOnClickListener(listener)
        binding.buttonMinus.setOnClickListener(listener)
        binding.buttonTimes.setOnClickListener(listener)
        binding.buttonBy.setOnClickListener(listener)

    }

    private val listener = View.OnClickListener {
        val value1 = binding.edittext1.text.toString().toIntOrNull()
        val value2 = binding.edittext2.text.toString().toIntOrNull()
        if (checkValue(it, value1, value2)) {
            when (it?.id) {
                binding.buttonPlus.id -> sendIntent(value1, value2, CalcSymbol.PLUS.key)
                binding.buttonMinus.id -> sendIntent(value1, value2, CalcSymbol.MINUS.key)
                binding.buttonTimes.id -> sendIntent(value1, value2, CalcSymbol.TIMES.key)
                binding.buttonBy.id -> sendIntent(value1, value2, CalcSymbol.BY.key)
            }
        }
    }

    private fun sendIntent(value1: Int?, value2: Int?, sym: String) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra(ExtraKey.VALUE1.key, value1)
        intent.putExtra(ExtraKey.VALUE2.key, value2)
        intent.putExtra(ExtraKey.SYM.key, sym)
        startActivity(intent)
    }

    private fun checkValue(view: View, value1: Int?, value2: Int?): Boolean {
        var msg = if (value1 == null && value2 == null) {
            "${binding.edittext1.hint}と${binding.edittext2.hint}に数字を入力してください"
        } else if (value1 == null) {
            "${binding.edittext1.hint}に数字を入力してください"
        } else if (value2 == null) {
            "${binding.edittext2.hint}に数字を入力してください"
        } else {
            return true
        }

        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show()
        return false
    }
}