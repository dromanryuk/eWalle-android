package ru.dromanryuk.ewalle.domain.model.location

data class City(
    val id: Int,
    val country: Country,
    val state: State?,
    val title: String
)