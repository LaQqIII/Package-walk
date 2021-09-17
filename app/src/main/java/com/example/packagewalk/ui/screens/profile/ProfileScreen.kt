package com.example.packagewalk.ui.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun Profile() {
    val numberPhone = remember { mutableStateOf("") }
    Column() {
        TextField(value = numberPhone.value, onValueChange = { numberPhone.value = it })
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Отправить код")
        }
    }
}