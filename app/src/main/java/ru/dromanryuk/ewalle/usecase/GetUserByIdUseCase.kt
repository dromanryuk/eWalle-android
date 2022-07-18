package ru.dromanryuk.ewalle.usecase

import ru.dromanryuk.ewalle.domain.model.user.User
import ru.dromanryuk.ewalle.domain.repository.UserRepository

class GetUserByIdUseCase(
    private val userRepository: UserRepository
) {
    operator fun invoke(id: Int): User = userRepository.getUserById(id)
}