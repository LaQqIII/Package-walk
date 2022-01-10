package com.example.packagewalk.data.references

data class User(
    val phoneNumber: String,
    val name: String,
    val markedForDeletion: Boolean = false
)
