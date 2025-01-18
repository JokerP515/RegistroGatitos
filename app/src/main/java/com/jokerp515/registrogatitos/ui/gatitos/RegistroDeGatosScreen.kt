package com.jokerp515.registrogatitos.ui.gatitos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.jokerp515.registrogatitos.viewmodel.gatitos.RegistroDeGatosViewModel
import kotlinx.coroutines.launch

@Composable
fun RegistroDeGatosScreen(viewModel: RegistroDeGatosViewModel) {
    var nombre by rememberSaveable { mutableStateOf("") }
    var genero by rememberSaveable { mutableStateOf("") }
    var edad by rememberSaveable { mutableStateOf("") }
    var peso by rememberSaveable { mutableStateOf("") }
    var color by rememberSaveable { mutableStateOf("") }
    var errorMessages by rememberSaveable { mutableStateOf<List<String>>(emptyList()) }
    var showSuccessMessage by remember { mutableStateOf(false) }

    // Crear SnackbarHostState
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            "Registro de Gato",
            modifier = Modifier.padding(bottom = 16.dp),
            style = MaterialTheme.typography.titleLarge
        )

        // Campos de entrada
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = genero,
            onValueChange = { genero = it },
            label = { Text("Género") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = edad,
            onValueChange = { if (it.all { char -> char.isDigit() }) edad = it },
            label = { Text("Edad (años)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = peso,
            onValueChange = { if (it.isFloatOrEmpty()) peso = it },
            label = { Text("Peso (kg)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = color,
            onValueChange = { color = it },
            label = { Text("Color") },
            modifier = Modifier.fillMaxWidth()
        )

        // Mensaje de error dinámico
        if (errorMessages.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = "Falta la siguiente información:",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.error
                )
                errorMessages.forEach { error ->
                    Text(
                        text = "- $error",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }

        // Botón de guardado
        Button(
            onClick = {
                val missingFields = checkMissingFields(nombre, genero, edad, peso, color)
                if (missingFields.isEmpty()) {
                    // Registrar el gato si todo está correcto
                    viewModel.agregarGato(
                        nombre,
                        genero,
                        edad.toInt(),
                        peso.toDouble(),
                        color
                    )
                    // Limpiar los campos después de guardar
                    nombre = ""
                    genero = ""
                    edad = ""
                    peso = ""
                    color = ""
                    errorMessages = emptyList() // Limpiar mensajes de error
                    showSuccessMessage = true
                } else {
                    // Mostrar los campos faltantes
                    errorMessages = missingFields
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Guardar")
        }
    }

    // Mostrar mensaje de éxito si el registro fue exitoso
    if (showSuccessMessage) {
        LaunchedEffect(true) {
            // Mostrar Snackbar utilizando SnackbarHostState
            scope.launch {
                snackbarHostState.showSnackbar("¡Gatito registrado con éxito!")
            }
            showSuccessMessage = false // Ocultar el mensaje después de mostrarlo
        }
    }

    // Mostrar Snackbar en la parte inferior
    SnackbarHost(hostState = snackbarHostState)
}

// Función para verificar campos faltantes
fun checkMissingFields(
    nombre: String,
    genero: String,
    edad: String,
    peso: String,
    color: String
): List<String> {
    val missing = mutableListOf<String>()
    if (nombre.isBlank()) missing.add("Nombre")
    if (genero.isBlank()) missing.add("Género")
    if (edad.isBlank()) missing.add("Edad")
    if (peso.isBlank()) missing.add("Peso")
    if (color.isBlank()) missing.add("Color")
    return missing
}

// Extensión para validar números flotantes
fun String.isFloatOrEmpty(): Boolean {
    return this.isEmpty() || this.toFloatOrNull() != null
}