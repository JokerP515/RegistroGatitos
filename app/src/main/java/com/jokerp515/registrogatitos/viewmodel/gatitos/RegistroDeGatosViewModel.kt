package com.jokerp515.registrogatitos.viewmodel.gatitos

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jokerp515.registrogatitos.data.Gato
import com.jokerp515.registrogatitos.local.dao.GatoDao
import com.jokerp515.registrogatitos.local.entities.GatoEntity
import com.jokerp515.registrogatitos.ui.events.GatoEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistroDeGatosViewModel
    @Inject constructor(
        private val gatoDao: GatoDao
    ) : ViewModel() {
    private val _listaDeGatos = mutableStateListOf<Gato>() // Posiblemente no necesario
    val listaDeGatos: List<Gato> get() = _listaDeGatos

    var gato by mutableStateOf(Gato())

    var gatoFlow = gatoDao.getAll()

//    fun agregarGato(nombre: String, genero: String, edad: Int, peso: Double, color: String) {
//        _listaDeGatos.add(
//            Gato(
//                null,
//                nombre,
//                genero,
//                edad,
//                peso,
//                color
//            )
//        )
//        viewModelScope.launch {
//            gatoDao.insert(
//                gato.toEntity()
//            )
//        }
//    }

    private fun agregarGato() {
        viewModelScope.launch {
            gatoDao.insert(gato.toEntity())
        }
    }

    fun onEvent(event: GatoEvent){
        when (event) {
            is GatoEvent.NameChanged -> {
                gato = gato.copy(nombre = event.name)
            }

            is GatoEvent.AgeChanged -> {
                gato = gato.copy(edad = event.age)
            }

            is GatoEvent.ColorChanged -> {
                gato = gato.copy(color = event.color)
            }

            is GatoEvent.GenderChanged -> {
                gato = gato.copy(genero = event.gender)
            }

            is GatoEvent.WeightChanged -> {
                gato = gato.copy(peso = event.weight)
            }
            is GatoEvent.onDelete -> {

            }
            GatoEvent.onSave -> {
                agregarGato()
                reset()
            }
        }
    }

    private fun reset(){
        gato = Gato()
    }

    fun eliminarGato(gato: GatoEntity){
        viewModelScope.launch{
            gato.id?.let { gatoDao.deleteById(it) }
        }
    }
}