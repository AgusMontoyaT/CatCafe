package com.example.catcafe.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.catcafe.R
import com.example.catcafe.ui.pantallas.PantallaActualizar
import com.example.catcafe.ui.pantallas.PantallaInicio
import com.example.catcafe.ui.pantallas.PantallaInsertar

enum class Pantallas(@StringRes val titulo: Int) {
    Inicio(titulo = R.string.pantalla_inicio),
    Insertar(titulo = R.string.insertar),
    Actualizar(titulo = R.string.actualizar)
}

@Composable
fun CatCafeApp(
    viewModel: GatoViewModel = viewModel(factory = GatoViewModel.Factory),
    navController: NavHostController = rememberNavController()
){
    val pilaRetroceso by navController.currentBackStackEntryAsState()

    val pantallaActual = Pantallas.valueOf(
        pilaRetroceso?.destination?.route ?: Pantallas.Inicio.name
    )

    Scaffold(
        topBar = {
            AppTopBar(
                pantallaActual = pantallaActual,
                puedeNavegarAtras = navController.previousBackStackEntry != null,
                onNavegarAtras = {navController.navigateUp()}
            )
        },
        floatingActionButton = {
            if(pantallaActual.titulo == R.string.pantalla_inicio) {
                FloatingActionButton(
                    onClick = { navController.navigate(route = Pantallas.Insertar.name) }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = stringResource(R.string.insertar_gato)
                    )
                }
            }
        }
    ) { innerPadding ->
        val uiState = viewModel.gatoUIState

        NavHost(
            navController = navController,
            startDestination = Pantallas.Inicio.name,
            modifier = Modifier.padding(innerPadding)
        ){
            // Grafo de las rutas
            composable(route = Pantallas.Inicio.name) {
                PantallaInicio(
                    appUIState = uiState,
                    onGatosObtenidos = {viewModel.obtenerGatos()},
                    onGatoEliminado = {viewModel.eliminarGato(it) },
                    onGatoPulsado = {
                        viewModel.actualizarGatoPulsado(it)
                        navController.navigate(Pantallas.Actualizar.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            composable(route = Pantallas.Insertar.name) {
                PantallaInsertar(
                    onInsertarPulsado = {
                        viewModel.insertarGato(it)
                        navController.popBackStack(Pantallas.Inicio.name, inclusive = false)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            composable(route = Pantallas.Actualizar.name) {
                PantallaActualizar(
                    gato = viewModel.gatoPulsado,
                    onGatoActualizado = {
                        viewModel.actualizarGato(it.id, it)
                        navController.popBackStack(Pantallas.Inicio.name, inclusive = false)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    pantallaActual: Pantallas,
    puedeNavegarAtras: Boolean,
    onNavegarAtras: () -> Unit,
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = { Text(text = stringResource(id = pantallaActual.titulo)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        navigationIcon = {
            if(puedeNavegarAtras) {
                IconButton(onClick = onNavegarAtras) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.atras)
                    )
                }
            }
        },
        modifier = modifier
    )
}