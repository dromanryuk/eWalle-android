package ru.dromanryuk.ewalle.domain.repository

import ru.dromanryuk.ewalle.domain.model.user.User

interface UserRepository {
    fun getUserById(id: Int): User
}