package ru.dromanryuk.ewalle.data.repository

import ru.dromanryuk.ewalle.R
import ru.dromanryuk.ewalle.domain.model.location.City
import ru.dromanryuk.ewalle.domain.model.location.Country
import ru.dromanryuk.ewalle.domain.model.location.State
import ru.dromanryuk.ewalle.domain.model.user.User
import ru.dromanryuk.ewalle.domain.repository.UserRepository

class UserRepositoryImpl : UserRepository {
    override fun getUserById(id: Int): User {
        return getUsersList().first { it.id == id }
    }
}

private fun getUsersList(): List<User> {
    val user2 = User(
        id = 2,
        name = "Mike",
        img = R.drawable.contact_1,
        city = City(2, Country(4, "Country 1", emptyList()), null, "City1"),
        balance = 12000.0,
        contacts = listOf()
    )
    val user3 = User(
        id = 3,
        name = "Joshpeh",
        img = R.drawable.contact_2,
        city = City(3, Country(3, "Country 2", emptyList()), null, "City2"),
        balance = 13000.0,
        contacts = listOf()
    )
    val user4 = User(
        id = 4,
        name = "Ashley",
        img = R.drawable.contact_3,
        city = City(4, Country(4, "Country 3", emptyList()), null,"City3"),
        balance = 14000.0,
        contacts = listOf()
    )
    val user1 = User(
        id = 1,
        name = "Carol Black",
        img = R.drawable.contact_4,
        city = City(1, Country(1, "USA", emptyList()), State(1, 1, "Washington"),"Seattle"),
        balance = 20000.600,
        contacts = listOf(user2, user3, user4)
    )
    return listOf(user1, user2, user3, user4)
}