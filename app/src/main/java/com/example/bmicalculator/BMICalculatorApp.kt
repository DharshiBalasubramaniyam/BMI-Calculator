package com.example.bmicalculator

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BMICalculatorApp(viewModel: BMICalculatorViewModel) {
    Column (
        modifier = Modifier.fillMaxSize().padding(10.dp)
    ){
        Text(text = "BMI Calculator", fontSize = 28.sp, modifier = Modifier.fillMaxWidth().padding(10.dp), textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.primary)

        Column (
            modifier = Modifier.fillMaxWidth().weight(0.3f).padding(10.dp).border(width = 1.5.dp, shape = RoundedCornerShape(10.dp), color = MaterialTheme.colorScheme.primary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            InputTextField(value = viewModel.height, onValueChange = viewModel::updateHeight, imeAction = ImeAction.Next)
            Text(text = "Your height (cm)", fontSize = 15.sp, modifier = Modifier.padding(10.dp))
        }

        Column (
            modifier = Modifier.fillMaxWidth().weight(0.3f).padding(10.dp).border(width = 1.5.dp, shape = RoundedCornerShape(10.dp), color = MaterialTheme.colorScheme.primary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            InputTextField(value = viewModel.weight, onValueChange = viewModel::updateWeight, imeAction = ImeAction.Done)
            Text(text = "Your Weight (Kg)", fontSize = 15.sp, modifier = Modifier.padding(10.dp))
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            OptionButton(text = "Clear", onClick = viewModel::clearAll)
            OptionButton(text = "Calculate", onClick = viewModel::execute)
        }

        Column (
            modifier = Modifier.fillMaxWidth().weight(0.4f).padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = viewModel.resultTitle, fontSize = 20.sp, modifier = Modifier.padding(10.dp))
            Text(text = viewModel.bmiValue, fontSize = 30.sp, modifier = Modifier.padding(10.dp), color = viewModel.resultColor)
            Text(text = viewModel.message, fontSize = 30.sp, modifier = Modifier.padding(10.dp), color = viewModel.resultColor)
            Text(text = viewModel.error, fontSize = 20.sp, modifier = Modifier.padding(top = 10.dp, start = 0.dp, bottom = 0.dp, end = 10.dp), color = Color.Red)
        }
    }
}