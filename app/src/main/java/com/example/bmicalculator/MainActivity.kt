package com.example.bmicalculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmicalculator.ui.theme.BMICalculatorTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMICalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BMICalculator()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BMICalculator() {
    var height by remember {
        mutableStateOf("0")
    }
    var weight by remember {
        mutableStateOf("0")
    }
    var bmi by remember {
        mutableStateOf("")
    }
    var result by remember {
        mutableStateOf("")
    }
    var result_title by remember {
        mutableStateOf("")
    }
    var result_color by remember {
        mutableStateOf(Color.Black)
    }
    var error by remember {
        mutableStateOf("")
    }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ){
        Text(
            text = "BMI Calculator",
            fontSize = 28.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary
        )
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.3f)
                .padding(10.dp)
                .border(
                    width = 1.5.dp,
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colorScheme.primary
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            BasicTextField(
                value = height,
                onValueChange = {
                    newHeight -> height = newHeight
                    bmi = ""
                    result = ""
                    result_title = ""
                    error = ""
                },
                textStyle = TextStyle(
                    fontSize = 45.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.secondary
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Next
                ),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary)
            )
            Text(
                text = "Your height (cm)",
                fontSize = 15.sp,
                modifier = Modifier.padding(10.dp)
            )
        }
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.3f)
                .padding(10.dp)
                .border(
                    width = 1.5.dp,
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colorScheme.primary
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            BasicTextField(
                value = weight,
                onValueChange = {
                    newWeight -> weight = newWeight
                    bmi = ""
                    result = ""
                    result_title = ""
                    error = ""
                },
                textStyle = TextStyle(
                    fontSize = 45.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.secondary
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Done
                ),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary)
            )
            Text(
                text = "Your Weight (Kg)",
                fontSize = 15.sp,
                modifier = Modifier.padding(10.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .weight(0.5f),
                onClick = {
                    height = ""
                    weight = ""
                }
            ) {
                Text(
                    text = "Clear",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp)
                )
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .weight(0.5f),
                onClick = {
                    try {
                        bmi = calculateBMI(height.toDouble(), weight.toDouble()).toString()
                        result = getResult(bmi.toDouble())
                        result_title = "Your BMI result"
                        result_color = getResultColor(bmi.toDouble())
                    }catch (e : Exception) {
                        error = "Invalid input!"
                    }
                }
            ) {
                Text(
                    text = "Calculate",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.4f)
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            Text(
                text = result_title,
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp)
            )
            Text(
                text = bmi,
                fontSize = 30.sp,
                modifier = Modifier.padding(10.dp),
                color = result_color
            )
            Text(
                text = result,
                fontSize = 30.sp,
                modifier = Modifier.padding(10.dp),
                color = result_color
            )
            Text(
                text = error,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 10.dp, start = 0.dp, bottom = 0.dp, end = 10.dp),
                color = Color.Red
            )


        }
    }
}

fun calculateBMI(height: Double, weight: Double) : Double{
    val bmi = (weight/height/height) * 10000
    return (bmi * 100).roundToInt() / 100.0
}

fun getResult(bmi: Double) : String {
    if (bmi < 18.5) return "Underweight"
    else if (bmi in 18.5..24.9) return "Normal"
    else if (bmi in 25.0..29.9) return "Overweight"
    else if (bmi >= 30) return "Obese"
    return ""
}

fun getResultColor(bmi: Double) : Color {
    if (bmi < 18.5) return Color.Blue
    else if (bmi in 18.5..24.9) return Color.Green
    else if (bmi in 24.9..29.9) return Color.Magenta
    return Color.Red
}

