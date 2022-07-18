package ru.dromanryuk.ewalle.usecase

import ru.dromanryuk.ewalle.domain.model.service.Service
import ru.dromanryuk.ewalle.domain.repository.ServicesRepository

class GetAllServicesUseCase(
    private val servicesRepository: ServicesRepository
) {
    operator fun invoke(): List<Service> = servicesRepository.getAllServices()
}