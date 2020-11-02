package com.exmple.tipcalculator

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.exmple.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener(View.OnClickListener { calculateTip() })
    }

    private fun calculateTip() {
        val stringInTextField = binding.edtCost.text.toString()

        val cost = stringInTextField.toDoubleOrNull()
        if (cost != null) {
            val selectedId = binding.radioGroup.checkedRadioButtonId
            val tipPercentage = when (selectedId) {
                R.id.rb_amazing -> 0.20
                R.id.rb_good -> 0.18
                else -> 0.15
            }
            var tip = cost * tipPercentage
            val roundUp = binding.swRoundUp.isChecked
            if (roundUp) {
                tip = kotlin.math.ceil(tip)
            }
            val formatTip = NumberFormat.getCurrencyInstance().format(tip)
            binding.txtAmount.text = getString(R.string.tip_amount, formatTip)

        } else {

            Toast.makeText(this, "Please Enter Cost of Service !!! ", Toast.LENGTH_LONG).show()

        }


    }
}