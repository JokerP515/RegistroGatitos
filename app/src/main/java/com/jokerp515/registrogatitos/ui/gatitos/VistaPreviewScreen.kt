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
import com.jokerp515.registrogatitos.data.Gato
import kotlin.random.Random

@Composable
fun VistaPreviewScreen() {
    val gatosAleatorios = generarGatosPreview(10)

    Column (
        modifier = Modifier.fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Vista Previa - Lista de Gatos",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(gatosAleatorios) { gato ->
                GatoCard(gato)
            }
        }
    }
}

fun generarGatosPreview(cantidad: Int): List<Gato> {
    val nombres = listOf("Milo", "Luna", "Simba", "Oliver", "Bella", "Leo", "Chloe", "Max", "Nala", "Daisy")
    val colores = listOf("Negro", "Blanco", "Gris", "Anaranjado", "Moteado", "Rayado")
    val generos = listOf("Macho", "Hembra")

    return List(cantidad) {
        Gato(
            nombre = nombres.random(),
            genero = generos.random(),
            edad = Random.nextInt(1, 15), // Edad entre 1 y 15 años
            peso = Random.nextDouble(2.0, 10.0), // Peso entre 2kg y 10kg
            color = colores.random()
        )
    }
}