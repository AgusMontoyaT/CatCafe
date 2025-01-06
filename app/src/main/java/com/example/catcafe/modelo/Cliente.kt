package com.example.catcafe.modelo

data class Cliente(
    val id: Int,
    val nombre: String,
    val telefono: String,
    val email: String,
    val suscrito: Boolean
)