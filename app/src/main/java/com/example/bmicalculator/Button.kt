package com.example.bmicalculator

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OptionButton(text: String, onclick: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        onClick = {
            onclick()
        }
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp)
        )
    }
}



