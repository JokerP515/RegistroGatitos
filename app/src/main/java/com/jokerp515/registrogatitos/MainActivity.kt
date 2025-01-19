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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jokerp515.registrogatitos.ui.gatitos.ListaDeGatosScreen
import com.jokerp515.registrogatitos.ui.gatitos.RegistroDeGatosScreen
import com.jokerp515.registrogatitos.ui.gatitos.VistaPreviewScreen
import com.jokerp515.registrogatitos.ui.theme.RegistroGatitosTheme
import com.jokerp515.registrogatitos.viewmodel.gatitos.RegistroDeGatosViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = RegistroDeGatosViewModel()
            AppNavigation(viewModel)
        }
    }
}

@Composable
fun AppNavigation(viewModel: RegistroDeGatosViewModel) {
    val navController = rememberNavController()
    RegistroGatitosTheme {
        Scaffold(
            bottomBar = { NavigationBar(navController) },
        ) { padding ->
            NavHost(
                navController = navController,
                startDestination = stringResource(R.string.registro),
                Modifier.padding(padding)
            ) {
                composable("registro") {
                    RegistroDeGatosScreen(viewModel)
                }
                composable("lista") {
                    ListaDeGatosScreen(viewModel)
                }
                composable("preview") {
                    VistaPreviewScreen()
                }
            }
        }
    }
}

@Composable
fun NavigationBar(navController: NavHostController) {
    NavigationBar (
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Add, contentDescription = stringResource(R.string.Registro)) },
            label = { Text(stringResource(R.string.Registro)) },
            selected = navController.currentBackStackEntry?.destination?.route == stringResource(R.string.registro),
            onClick = { navController.navigate("registro") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = stringResource(R.string.Lista)) },
            label = { Text(stringResource(R.string.Lista)) },
            selected = navController.currentBackStackEntry?.destination?.route == stringResource(R.string.lista),
            onClick = { navController.navigate("lista") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Info, contentDescription = stringResource(R.string.Preview)) },
            label = { Text(stringResource(R.string.Vista_Previa)) },
            selected = navController.currentBackStackEntry?.destination?.route == stringResource(R.string.preview),
            onClick = { navController.navigate("preview") }
        )
    }
}