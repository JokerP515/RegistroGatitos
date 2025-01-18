package com.jokerp515.registrogatitos.ui.gatitos

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jokerp515.registrogatitos.data.Gato

@Composable
fun GatoCard(gato: Gato) {
    // Estado para controlar la expansión de la tarjeta
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Mostrar el nombre del gato
            Text(
                text = gato.nombre,
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            // Animación para expandir o contraer la información adicional
            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically(animationSpec = tween(300) ) + fadeIn(),
                exit = shrinkVertically(animationSpec = tween(300)) + fadeOut()
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text(text = "Género: ${gato.genero}")
                    Text(text = "Edad: ${gato.edad} años")
                    Text(text = "Peso: ${"%.2f".format(gato.peso)} kg")
                    Text(text = "Color: ${gato.color}")
                }
            }

            // Botón para desplegar/contraer
            TextButton(
                onClick = { expanded = !expanded },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = if (expanded) "Mostrar menos" else "Mostrar más")
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null
                )
            }
        }
    }
}