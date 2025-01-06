package com.example.catcafe.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.catcafe.GatoAplicacion
import com.example.catcafe.datos.GatoRepositorio
import com.example.catcafe.modelo.Gato
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface GatoUIState {
    data class ObtenerExito(val gatos: List<Gato>) : GatoUIState
    data class CrearExito(val gato: Gato) : GatoUIState
    data class ActualizarExito(val gato: Gato) : GatoUIState
    data class EliminarExito(val id: String) : GatoUIState

    object Error : GatoUIState
    object Cargando : GatoUIState
}

class GatoViewModel(private val gatoRepositorio: GatoRepositorio) : ViewModel() {
    var gatoUIState: GatoUIState by mutableStateOf(GatoUIState.Cargando)
        private set

    var gatoPulsado: Gato by mutableStateOf(Gato("", "", "", "", "", ""))
        private set

    fun actualizarGatoPulsado(gato: Gato){
        gatoPulsado = gato
    }

    init{
        obtenerGatos()
    }

    fun obtenerGatos() {
        viewModelScope.launch {
            gatoUIState = GatoUIState.Cargando
            gatoUIState = try {
                val listaGatos = gatoRepositorio.obtenerGato()
                GatoUIState.ObtenerExito(listaGatos)
            } catch (e: IOException){
                GatoUIState.Error
            } catch (e: HttpException){
                GatoUIState.Error
            }
        }
    }

    fun insertarGato(gato: Gato) {
        viewModelScope.launch {
            gatoUIState = GatoUIState.Cargando
            gatoUIState = try {
                val gatoInsertado = gatoRepositorio.insertarGato(gato)
                GatoUIState.CrearExito(gatoInsertado)
            } catch (e: IOException){
                GatoUIState.Error
            } catch (e: HttpException){
                GatoUIState.Error
            }
        }
    }

    fun actualizarGato(id: String, gato: Gato) {
        viewModelScope.launch {
            gatoUIState = GatoUIState.Cargando
            gatoUIState = try {
                val gatoActualizado = gatoRepositorio.actualizarGato(
                    id = id,
                    gato = gato
                )
                GatoUIState.ActualizarExito(gatoActualizado)
            } catch (e: IOException){
                GatoUIState.Error
            } catch (e: HttpException){
                GatoUIState.Error
            }
        }
    }

    fun eliminarGato(id: String) {
        viewModelScope.launch {
            gatoUIState = GatoUIState.Cargando
            gatoUIState = try {
                gatoRepositorio.eliminarGato(id)
                GatoUIState.EliminarExito(id)
            } catch (e: IOException){
                GatoUIState.Error
            } catch (e: HttpException){
                GatoUIState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val aplicacion = (this[APPLICATION_KEY] as GatoAplicacion)
                val gatoRepositorio = aplicacion.contenedor.gatoRepositorio
                GatoViewModel(gatoRepositorio = gatoRepositorio)
            }
        }
    }
}

