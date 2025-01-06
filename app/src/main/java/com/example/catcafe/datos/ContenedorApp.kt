package com.example.catcafe.datos

import GatoServicioApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface ContenedorApp {
    val gatoRepositorio: GatoRepositorio
}

class GatoContenedorApp : ContenedorApp {
    private val baseUrl = "192.168.1.141:3000"

    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val servicioRetrofit: GatoServicioApi by lazy {
        retrofit.create(GatoServicioApi::class.java)
    }

    override val gatoRepositorio: GatoRepositorio by lazy {
        ConexionGatoRepositorio(servicioRetrofit)
    }
}