package com.example.tiptime

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mostrarGorjeta(0.0)
        binding.calculateButton.setOnClickListener {
            calcularGorjeta()
        }
        binding.costOfServiceTextedit.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(view, keyCode)
        }
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }

    private fun mostrarGorjeta(gorjeta: Double) {
        val ptBr = Locale("pt", "BR");
        val gorjetaFormatada = NumberFormat.getCurrencyInstance(ptBr).format(gorjeta)
        binding.tipResult.text = getString(R.string.valor_da_gorjeta, gorjetaFormatada)
    }

    private fun calcularGorjeta() {
        val GorjetaString = binding.costOfServiceTextedit.text
        val custo = GorjetaString.toString().toDoubleOrNull()
        if (custo == null) {
            mostrarGorjeta(0.0)
            return
        }
        val porcentagem = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.twenty_percent -> 0.20
            R.id.eighteen_percent -> 0.18
            else -> 0.15
        }
        var gorjeta = custo * porcentagem

        if (binding.roundUpSwitch.isChecked) {
            gorjeta = kotlin.math.ceil(gorjeta)
        }
        mostrarGorjeta(gorjeta)
    }
}