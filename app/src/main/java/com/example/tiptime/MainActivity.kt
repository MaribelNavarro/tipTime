package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Espera a que el boton de Calculate sea presionado.
        binding.calculateButton.setOnClickListener{
            calculateTip() }
    }

    //Método para calcular la propina
    fun calculateTip() {
        //Obtinen los valores dados por el usuario

        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null) {
            binding.tipResult.text = ""
            return
        }
        val selectedId = binding.tipOptions.checkedRadioButtonId

        val tipPercentage = when (selectedId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        //Calcula la propina con los valores dados
        var tip = tipPercentage * cost
        val roundUp = binding.roundUpSwitch.isChecked
        //Rendondea la propina
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }
        //Le da formato al valor de la propina
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        //Mostrar la propina en el elemento TextView
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}