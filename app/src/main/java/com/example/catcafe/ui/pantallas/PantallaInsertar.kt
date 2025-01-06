package com.example.catcafe.ui.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.catcafe.modelo.Gato


@Composable
fun PantallaInsertar(
    onInsertarPulsado: (Gato) -> Unit,
    modifier: Modifier = Modifier
){
    var nombre by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var raza by remember { mutableStateOf("") }
    var estadoAdopcion by remember { mutableStateOf("") }
    var caracteristicas by remember { mutableStateOf("") }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
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
            value = estadoAdopcion,
            label =  { Text(text = "Estado de la adopción") },
            onValueChange = {estadoAdopcion = it}
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
                val gato = Gato(nombre = nombre, edad=edad, raza = raza, estado_adopcion = estadoAdopcion, caracteristicas = caracteristicas)
                onInsertarPulsado(gato)
            }) {
            Text(text = "Insertar")
        }
    }
}