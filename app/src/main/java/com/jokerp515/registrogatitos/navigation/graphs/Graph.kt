package com.jokerp515.registrogatitos.navigation.graphs

import kotlinx.serialization.Serializable

sealed class Graph {
    @Serializable
    data object GraficoGatos : Graph()
}
