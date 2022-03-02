package jp.techacademy.keita.doi.calcapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import jp.techacademy.keita.doi.calcapp.databinding.ActivityMainBinding

val LOG_TAG = "CalcApp"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonPlus.setOnClickListener { sendIntent("+") }
        binding.buttonMinus.setOnClickListener { sendIntent("-") }
        binding.buttonTimes.setOnClickListener { sendIntent("*") }
        binding.buttonBy.setOnClickListener { sendIntent("/") }
    }

    private fun sendIntent(sym: String) {
        val intent = Intent(this, ResultActivity::class.java)
        val value1 = binding.edittext1.text.toString().toIntOrNull()
        val value2 = binding.edittext2.text.toString().toIntOrNull()
        intent.putExtra(ExtraKey.VALUE1.key, value1)
        intent.putExtra(ExtraKey.VALUE2.key, value2)
        intent.putExtra(ExtraKey.SYM.key, sym)
        startActivity(intent)
    }
}