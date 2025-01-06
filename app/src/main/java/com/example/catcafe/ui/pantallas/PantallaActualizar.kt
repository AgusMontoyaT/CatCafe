package com.example.catcafe.ui.pantallas

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.catcafe.modelo.Gato



@Composable
fun PantallaActualizar(
    gato: Gato,
    onGatoActualizado: (Gato) -> Unit,
    modifier: Modifier = Modifier
) {
    var nombre by remember { mutableStateOf(gato.nombre) }
    var edad by remember { mutableStateOf(gato.edad) }
    var raza by remember { mutableStateOf(gato.raza) }
    var estado_adopcion by remember { mutableStateOf(gato.estado_adopcion) }
    var caracteristicas by remember { mutableStateOf(gato.caracteristicas) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Spacer(Modifier.height(16.dp))

        TextField(
            value = gato.id,
            label =  { Text(text = "ID") },
            onValueChange = {},
            enabled = false
        )

        Spacer(Modifier.height(16.dp))

      TextField(
            value = nombre,
            label =  { Text(text = "Nombre") },
            onValueChange = {nombre = it}
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = edad,
            label =  { Text(text = "Edad") },
            onValueChange = {edad = it}
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = raza,
            label =  { Text(text = "Raza") },
            onValueChange = {raza = it}
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = estado_adopcion,
            label =  { Text(text = "Estado de la adopción") },
            onValueChange = {estado_adopcion = it}
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = caracteristicas,
            label =  { Text(text = "Características") },
            onValueChange = {caracteristicas = it}
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                val gatoActualizado = Gato(id = gato.id, nombre = nombre, edad=edad, raza = raza, estado_adopcion = estado_adopcion, caracteristicas = caracteristicas)
                onGatoActualizado(gatoActualizado)
            }) {
            Text(text = "Actualizar")
        }
    }
}