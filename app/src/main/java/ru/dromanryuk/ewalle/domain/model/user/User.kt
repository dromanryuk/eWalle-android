package ru.dromanryuk.ewalle.domain.model.user

import ru.dromanryuk.ewalle.domain.model.location.City

data class User(
    val id: Int,
    val name: String,
    val img: Int,
    val city: City,
    val balance: Double,
    val contacts: List<User>?
)
