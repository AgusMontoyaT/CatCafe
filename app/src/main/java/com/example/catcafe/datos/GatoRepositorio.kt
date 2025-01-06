package com.example.catcafe.datos

import GatoServicioApi
import com.example.catcafe.modelo.Gato

interface GatoRepositorio {
    suspend fun obtenerGato(): List<Gato>
    suspend fun insertarGato(gato: Gato): Gato
    suspend fun actualizarGato(id: String, gato: Gato): Gato
    suspend fun eliminarGato(id: String): Gato
}

class ConexionGatoRepositorio(
    private val gatoServicioApi: GatoServicioApi
) : GatoRepositorio {
    override suspend fun obtenerGato(): List<Gato> = gatoServicioApi.obtenerGato()
    override suspend fun insertarGato(gato: Gato):Gato = gatoServicioApi.insertarGato(gato)
    override suspend fun actualizarGato(id: String, gato: Gato): Gato  = gatoServicioApi.actualizarGato(id, gato)
    override suspend fun eliminarGato(id: String): Gato = gatoServicioApi.eliminarGato(id)
}