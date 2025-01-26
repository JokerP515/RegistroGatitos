package com.jokerp515.registrogatitos.ui.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import androidx.navigation.compose.composable
import com.jokerp515.registrogatitos.ui.navigation.routes.Routes
import com.jokerp515.registrogatitos.ui.screen.ListaDeGatosScreen
import com.jokerp515.registrogatitos.ui.screen.RegistroDeGatosScreen
import com.jokerp515.registrogatitos.ui.screen.VistaPreviewScreen
import com.jokerp515.registrogatitos.viewmodel.gatitos.RegistroDeGatosViewModel

fun NavGraphBuilder.graficoGatos(viewModel: RegistroDeGatosViewModel, go: (Any) -> Unit) {
    navigation<Graph.GraficoGatos>(startDestination = Routes.Registro) {
        composable<Routes.Registro> {
            RegistroDeGatosScreen(viewModel)
        }
        composable<Routes.Lista> {
            ListaDeGatosScreen(viewModel, go = go)
        }
        composable<Routes.VistaPrevia> {
            VistaPreviewScreen(viewModel, go = go)
        }
    }
}
