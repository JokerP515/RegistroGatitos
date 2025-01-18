package com.jokerp515.registrogatitos.ui.gatitos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jokerp515.registrogatitos.viewmodel.gatitos.RegistroDeGatosViewModel

@Composable
fun ListaDeGatosScreen(viewModel: RegistroDeGatosViewModel) {
    Column (
        modifier = Modifier.fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Lista de Gatos Registrados",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            items(viewModel.listaDeGatos){ gato ->
                GatoCard(gato)
            }
        }
    }
}

//@Composable
//fun GatoCard(gato: Gato) {
//    Card(
//        modifier = Modifier.fillMaxWidth(),
//        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text(text = "Nombre: ${gato.Nombre}")
//            Text(text = "Género: ${gato.Genero}")
//            Text(text = "Edad: ${gato.Edad} años")
//            Text(text = "Peso: ${gato.Peso} kg")
//            Text(text = "Color: ${gato.Color}")
//        }
//    }
//}