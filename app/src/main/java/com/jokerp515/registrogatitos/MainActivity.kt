package com.jokerp515.registrogatitos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.jokerp515.registrogatitos.ui.navigation.graphs.Graph
import com.jokerp515.registrogatitos.ui.navigation.graphs.graficoGatos
import com.jokerp515.registrogatitos.ui.navigation.routes.Routes
import com.jokerp515.registrogatitos.ui.theme.RegistroGatitosTheme
import com.jokerp515.registrogatitos.viewmodel.gatitos.RegistroDeGatosViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation(viewModel: RegistroDeGatosViewModel = viewModel()) {
    val navController = rememberNavController()
    RegistroGatitosTheme {
        Scaffold(
            bottomBar = { NavigationBar(navController.currentBackStackEntry?.destination?.route.toString()) {
                navController.navigate(
                    it
                )
            }
            },
        ) { padding ->
            NavHost(
                navController = navController,
                startDestination = Graph.GraficoGatos,
                Modifier.padding(padding)
            ) {
                graficoGatos(viewModel){ route ->
                    navController.navigate(route)
                }
            }
        }
    }
}

@Composable
fun NavigationBar(pantallaActual: String = "" ,go: (Any) -> Unit = {}) {
    NavigationBar (
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Add, contentDescription = stringResource(R.string.Registro)) },
            label = { Text(stringResource(R.string.Registro)) },
            selected = pantallaActual == Routes.Registro.id,
            onClick = { go(Routes.Registro) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = stringResource(R.string.Lista)) },
            label = { Text(stringResource(R.string.Lista)) },
            selected = pantallaActual == Routes.Lista.id,
            onClick = { go(Routes.Lista) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Info, contentDescription = stringResource(R.string.Preview)) },
            label = { Text(stringResource(R.string.Vista_Previa)) },
            selected = pantallaActual == Routes.VistaPrevia.id,
            onClick = { go(Routes.VistaPrevia) }
        )
    }
}