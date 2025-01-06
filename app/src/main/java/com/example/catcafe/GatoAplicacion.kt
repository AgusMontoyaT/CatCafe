package com.example.catcafe

import android.app.Application
import com.example.catcafe.datos.ContenedorApp
import com.example.catcafe.datos.GatoContenedorApp

class GatoAplicacion : Application() {
        lateinit var contenedor: ContenedorApp
        override fun onCreate() {
            super.onCreate()
            contenedor = GatoContenedorApp()
        }
    }
