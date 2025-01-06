package com.example.catcafe.ui.pantallas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.catcafe.R
import com.example.catcafe.modelo.Gato
import com.example.catcafe.ui.GatoUIState

@Composable
fun PantallaInicio(
    appUIState: GatoUIState,
    onGatosObtenidos: () -> Unit,
    onGatoPulsado: (Gato) -> Unit,
    onGatoEliminado: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    when (appUIState) {
        is GatoUIState.Cargando -> PantallaCargando(modifier = modifier.fillMaxSize())
        is GatoUIState.Error -> PantallaError(modifier = modifier.fillMaxWidth())
        is GatoUIState.ObtenerExito -> PantallaListaGatos(
            lista = appUIState.gatos,
            onGatoPulsado = onGatoPulsado,
            onGatoEliminado = onGatoEliminado,
            modifier = modifier.fillMaxWidth()
        )
        is GatoUIState.CrearExito -> onGatosObtenidos()
        is GatoUIState.ActualizarExito -> onGatosObtenidos()
        is GatoUIState.EliminarExito -> onGatosObtenidos()
    }
}

@Composable
fun PantallaCargando(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.cargando),
        contentDescription = stringResource(R.string.cargando)
    )
}

@Composable
fun PantallaError(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.error),
        contentDescription = stringResource(R.string.error)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PantallaListaGatos(
    lista: List<Gato>,
    onGatoPulsado: (Gato) -> Unit,
    onGatoEliminado: (String) -> Unit,
    modifier: Modifier = Modifier
){
    LazyColumn(modifier = modifier) {
        items(lista){ gato ->
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .combinedClickable(
                        onClick = { onGatoPulsado(gato) },
                        onLongClick = { onGatoEliminado(gato.id) }
                    )
            ){
                Column(
                    modifier= Modifier.fillMaxWidth()
                ){
                    Text(
                        text = gato.nombre
                    )
                    Text(
                        text = gato.raza
                    )
                    Text(
                        text = gato.edad
                    )
                    Text(
                        text = gato.estado_adopcion
                    )

                    HorizontalDivider()
                }

            }
        }
    }
}