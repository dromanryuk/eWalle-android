package ru.dromanryuk.ewalle.domain.model.location

data class Country(
    val id: Int,
    val title: String,
    val states: List<State>?
)
