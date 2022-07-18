package ru.dromanryuk.ewalle.presentation.home

import ru.dromanryuk.ewalle.domain.repository.ServicesRepository
import ru.dromanryuk.ewalle.domain.repository.UserRepository
import ru.dromanryuk.ewalle.usecase.GetAllServicesUseCase
import ru.dromanryuk.ewalle.usecase.GetUserByIdUseCase

class HomeUseCases(
    userRepository: UserRepository,
    servicesRepository: ServicesRepository
) {
    val getUserByIdUseCase = GetUserByIdUseCase(userRepository)
    val getAllServicesUseCase = GetAllServicesUseCase(servicesRepository)
}