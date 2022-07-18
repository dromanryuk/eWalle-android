package ru.dromanryuk.ewalle.domain.repository

import ru.dromanryuk.ewalle.domain.model.service.Service

interface ServicesRepository {
    fun getAllServices(): List<Service>
}