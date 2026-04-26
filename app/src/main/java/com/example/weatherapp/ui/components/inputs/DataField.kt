package com.example.weatherapp.ui.components.inputs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DataInput(
    state: String,
    label: String,
    modifier: Modifier,
    onValueChange: (String) -> Unit
){

    OutlinedTextField(
        value = state,
        label = { Text(text = label) },
        modifier = modifier.fillMaxWidth(0.9f),
        onValueChange = onValueChange
    )
}