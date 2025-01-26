package com.jokerp515.registrogatitos.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.jokerp515.registrogatitos.R
import com.jokerp515.registrogatitos.ui.gatitos.GatoCard
import com.jokerp515.registrogatitos.viewmodel.gatitos.RegistroDeGatosViewModel

@Composable
fun ListaDeGatosScreen(viewModel: RegistroDeGatosViewModel, go: (Any) -> Unit) {

    val listaDeGatos by viewModel.gatoFlow.collectAsState(initial = emptyList())

    ListaScreen(
        titulo = stringResource(R.string.lista_de_gatos_registrados),
        listaDeGatos
    ) { gato ->
        GatoCard(gato)
    }
}