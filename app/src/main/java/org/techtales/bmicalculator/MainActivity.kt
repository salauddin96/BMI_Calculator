package org.techtales.bmicalculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import org.techtales.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.calculation.setOnClickListener{
            val weight = binding.weightInput.text.toString()
            val height = binding.heightInput.text.toString()

            if (validateInput(weight,height)){
                val bmi = weight.toDouble()/((height.toDouble()/100)*(height.toDouble()/100))
                val bmiDigit = String.format("%.2f", bmi).toDouble()
                displayResult(bmiDigit)
            }
        }
    }
    private fun displayResult(bmi:Double){
        binding.bmiResult.text = bmi.toString()
        binding.healthStatus.text = "You are Healthy"
        binding.bmiRange.text = "(Normal range is 18.5 - 24.5)"

        var result = ""
        var color = 0
        var range = ""

        when{
            bmi < 18.5 ->{
                result = "Underweight"
                color = R.color.under_weight
                range = "(Underweight range is less than 18.50)"
            }
            bmi in 18.5..24.99 ->{
                result = "Healthy"
                color = R.color.normal
                range = "(Health range is 18.50 to 24.99)"
            }
            bmi in 25.00..29.99 ->{
                result = "Underweight"
                color = R.color.over_weight
                range = "(Overweight range is less than 18.50)"
            }
            bmi > 29.99 ->{
                result = "Obese"
                color = R.color.obese
                range = "(Obese range is greater than 29.99)"
            }
        }
        binding.healthStatus.text = result
        binding.healthStatus.setTextColor(ContextCompat.getColor(this, color))
        binding.bmiRange.text = range
        binding.bmiRange.setTextColor(ContextCompat.getColor(this, color))

    }

   private fun validateInput(weight:String?, height:String?):Boolean{
        return when{
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "Weight is empty", Toast.LENGTH_SHORT).show()
                return false
            }
            height.isNullOrEmpty()-> {
                Toast.makeText(this, "Height is empty", Toast.LENGTH_SHORT).show()
                return false
            }

            else -> {
                return true
            }
        }
    }
}