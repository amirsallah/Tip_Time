package com.example.tiptime

import android.icu.text.NumberFormat
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.tipResult.text = getString(R.string.tip_amount, "$00.00")
        binding.button.setOnClickListener { calculateTip() }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun calculateTip() {
        val cost = binding.costOfServiceEditText.text.toString().toDoubleOrNull()

        if (cost == null) {
            binding.tipResult.text = getString(R.string.tip_amount, "")
            return
        }

        val tipPercentage = when (binding.tipOption.checkedRadioButtonId) {
            R.id.option_eighteen_percent -> 0.18
            R.id.option_fifteen_percent -> 0.15
            else -> 0.20
        }

        var tip = cost * tipPercentage
        val round = binding.roundUpSwitch.isChecked

        if (round)
            tip = kotlin.math.ceil(tip)

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}