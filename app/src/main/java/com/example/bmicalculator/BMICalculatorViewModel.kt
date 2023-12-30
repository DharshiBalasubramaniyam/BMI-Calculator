package com.example.bmicalculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import java.lang.Math.round
import kotlin.math.roundToInt

class BMICalculatorViewModel : ViewModel() {
    var height by mutableStateOf("0")
    var weight by mutableStateOf("0")
    var bmiValue by mutableStateOf("")
    var message by mutableStateOf("")
    var resultColor by mutableStateOf(Color.Black)
    var resultTitle by mutableStateOf("")
    var error by mutableStateOf("")

    fun updateWeight(it: String){
        weight = it
        clearResult()
    }

    fun updateHeight(it: String){
        height = it
        clearResult()
    }

    private fun calculateBMI(){
        val value =
            ((weight.toDouble() / height.toDouble() / height.toDouble()) * 10000 * 100).roundToInt() / 100.0
        bmiValue = value.toString()
    }

    private fun updateMessage(){
        message = when {
            bmiValue.toDouble() < 18.5 -> "Underweight"
            bmiValue.toDouble() in 18.5..24.9 -> "Normal"
            bmiValue.toDouble() in 25.0..29.9 -> "Overweight"
            bmiValue.toDouble() >= 30.0 -> "Obese"
            else -> "Error"
        }
    }


    private fun updateResultColor() {
        resultColor =  when {
            bmiValue.toDouble() < 18.5 -> Color.Blue
            bmiValue.toDouble() in 18.5..24.9 -> Color.Green
            bmiValue.toDouble() in 25.0..29.9 -> Color.Magenta
            bmiValue.toDouble() >= 30.0 -> Color.Red
            else -> Color.Red
        }

    }

    fun execute() {
        try {
            calculateBMI()
            updateMessage()
            updateResultColor()
            resultTitle = "Your result is"
        }catch(_: Exception) {
            error = "Invalid input!"
        }
    }

    private fun clearResult() {
        bmiValue = ""
        message = ""
        resultTitle = ""
        error = ""
    }

    fun clearAll() {
        height = ""
        weight = ""
        clearResult()
    }


}