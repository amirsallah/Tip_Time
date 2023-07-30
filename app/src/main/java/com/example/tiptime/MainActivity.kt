package com.example.tiptime

import android.icu.text.NumberFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val button : Button = findViewById(R.id.button)
        button.setOnClickListener {
            calculateTip()
        }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun calculateTip(){
        val cost = findViewById<EditText?>(R.id.cost_of_service).text.toString().toDouble()
        val tipPercentage = when (findViewById<RadioGroup?>(R.id.tip_option).checkedRadioButtonId){
            R.id.option_eighteen_percent -> 0.18
            R.id.option_fifteen_percent -> 0.15
            else -> 0.20
        }
        var tip = cost * tipPercentage
        val round = findViewById<Switch?>(R.id.round_up_switch).isChecked

        if (round)
            tip = kotlin.math.ceil(tip)

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        findViewById<TextView>(R.id.tip_result).text = getString(R.string.tip_amount, formattedTip)
    }
}