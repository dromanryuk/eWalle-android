package ru.dromanryuk.ewalle.data.di

import org.koin.dsl.module
import ru.dromanryuk.ewalle.data.repository.ServicesRepositoryImpl
import ru.dromanryuk.ewalle.data.repository.UserRepositoryImpl
import ru.dromanryuk.ewalle.domain.repository.ServicesRepository
import ru.dromanryuk.ewalle.domain.repository.UserRepository

val ewalleDataModule = module {
    single<UserRepository> { UserRepositoryImpl() }
    single<ServicesRepository> { ServicesRepositoryImpl() }
}