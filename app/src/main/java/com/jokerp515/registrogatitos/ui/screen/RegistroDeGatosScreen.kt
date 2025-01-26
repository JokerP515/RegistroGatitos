package com.jokerp515.registrogatitos.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.jokerp515.registrogatitos.R
import com.jokerp515.registrogatitos.ui.events.GatoEvent
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

    var gato = viewModel.gato

    // Crear SnackbarHostState
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Dado el caso el dispositivo sea pequeño, se puede hacer scroll
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            stringResource(R.string.registro_de_un_gatito),
            modifier = Modifier.padding(bottom = 16.dp),
            style = MaterialTheme.typography.titleLarge
        )

        // Campos de entrada
        OutlinedTextField(
            value = gato.nombre,
            onValueChange = { viewModel.onEvent(GatoEvent.NameChanged(it)) },
            label = { Text(stringResource(R.string.nombre)) },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = gato.genero,
            onValueChange = { viewModel.onEvent(GatoEvent.GenderChanged(it)) },
            label = { Text(stringResource(R.string.genero)) },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = gato.edad.toString(),
            onValueChange = { //if (it.all { char -> char.isDigit() }) edad = it
                val edadInt = it.toIntOrNull()
                if (edadInt != null) {
                    viewModel.onEvent(GatoEvent.AgeChanged(edadInt))
                }
            },
            label = { Text(stringResource(R.string.edad)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = gato.peso.toString(),
            onValueChange = {
                val pesoDouble = it.toDoubleOrNull()
                if (pesoDouble != null) {
                    viewModel.onEvent(GatoEvent.WeightChanged(pesoDouble))
                }
            }, //if (it.isFloatOrEmpty()) peso = it
            label = { Text(stringResource(R.string.peso_kg)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = gato.color,
            onValueChange = { viewModel.onEvent(GatoEvent.ColorChanged(it)) },
            label = { Text(stringResource(R.string.color)) },
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
                    text = stringResource(R.string.falta_la_siguiente_informacion),
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
                val missingFields = checkMissingFields(gato.nombre, gato.genero, gato.edad.toString(), gato.peso.toString(), gato.color)
                if (missingFields.isEmpty()) {
//                    // Registrar el gato si todo está correcto
//                    viewModel.agregarGato(
//                        nombre,
//                        genero,
//                        edad.toInt(),
//                        peso.toDouble(),
//                        color
//                    )
//                    // Limpiar los campos después de guardar
//                    nombre = ""
//                    genero = ""
//                    edad = ""
//                    peso = ""
//                    color = ""
                    viewModel.onEvent(GatoEvent.onSave)
                    errorMessages = emptyList() // Limpiar mensajes de error
                    showSuccessMessage = true
                } else {
                    // Mostrar los campos faltantes
                    errorMessages = missingFields
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.guardar))
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