package com.example.packagewalk.ui.screens.authorization

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun Authorization() {
    val numberPhone = remember { mutableStateOf("") }
    Column() {
        TextField(value = numberPhone.value, onValueChange = { numberPhone.value = it })
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Отправить код")
        }
    }
}