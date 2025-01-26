package com.jokerp515.registrogatitos.ui.events

sealed class GatoEvent {
    data class NameChanged(val name: String) : GatoEvent()
    data class GenderChanged(val gender: String) : GatoEvent()
    data class AgeChanged(val age: Int) : GatoEvent()
    data class WeightChanged(val weight: Double) : GatoEvent()
    data class ColorChanged(val color: String) : GatoEvent()
    data object onSave : GatoEvent()
    data class onDelete(val catId: Long) : GatoEvent()
}