package com.jokerp515.registrogatitos.viewmodel.gatitos

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.jokerp515.registrogatitos.data.Gato

class RegistroDeGatosViewModel : ViewModel() {
    private val _listaDeGatos = mutableStateListOf<Gato>()
    val listaDeGatos: List<Gato> get() = _listaDeGatos

    fun agregarGato(nombre: String, genero: String, edad: Int, peso: Double, color: String) {
        _listaDeGatos.add(
            Gato(
                nombre,
                genero,
                edad,
                peso,
                color
            )
        )
    }
}