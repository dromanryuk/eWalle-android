package ru.dromanryuk.ewalle.presentation.menu

import ru.dromanryuk.ewalle.domain.repository.UserRepository
import ru.dromanryuk.ewalle.usecase.GetUserByIdUseCase

class MenuUseCases(
    userRepository: UserRepository
) {
    val getUserByIdUseCase = GetUserByIdUseCase(userRepository)
}