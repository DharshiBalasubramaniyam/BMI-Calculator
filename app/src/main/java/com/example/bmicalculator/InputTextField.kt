package com.example.bmicalculator

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun InputTextField(value: String, onValueChange: (String) -> Unit, imeAction: ImeAction) {
    BasicTextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        textStyle = TextStyle(
            fontSize = 45.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.secondary
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = imeAction
        ),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary)
    )
}