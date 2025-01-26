package com.jokerp515.registrogatitos.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.jokerp515.registrogatitos.R
import com.jokerp515.registrogatitos.data.Gato
import com.jokerp515.registrogatitos.local.entities.GatoEntity
import com.jokerp515.registrogatitos.ui.gatitos.GatoCard
import kotlin.random.Random

@Composable
fun VistaPreviewScreen() {
    val gatosAleatorios = generarGatosPreview(10)

    ListaScreen(
        titulo = stringResource(R.string.vista_previa_lista_de_gatitos),
        gatos = gatosAleatorios
    ) { gato ->
        GatoCard(gato, onDelete = {} ) // Función OnDelete no funcional en vista previa
    }
}

fun generarGatosPreview(cantidad: Int): List<GatoEntity> {
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
        ).toEntity()
    }
}