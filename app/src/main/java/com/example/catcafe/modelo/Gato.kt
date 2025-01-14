package com.example.catcafe.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Gato(
    @SerialName(value = "id")
    val id: String = "",
    @SerialName(value = "nombre")
    val nombre: String,
    @SerialName(value = "edad")
    val edad: String,
    @SerialName(value = "raza")
    val raza: String,
    @SerialName(value = "estado_adopcion")
    val estado_adopcion: String,
    @SerialName(value = "caracteristicas")
    val caracteristicas: String
)