package ru.dromanryuk.ewalle.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.dromanryuk.ewalle.presentation.home.HomeUseCases
import ru.dromanryuk.ewalle.presentation.home.HomeViewModel
import ru.dromanryuk.ewalle.presentation.login.LoginViewModel
import ru.dromanryuk.ewalle.presentation.menu.MenuUseCases
import ru.dromanryuk.ewalle.presentation.menu.MenuViewModel

val ewallePresentationModule = module {
    viewModel { LoginViewModel() }

    single { HomeUseCases(get(), get()) }
    viewModel { HomeViewModel(get()) }

    single { MenuUseCases(get()) }
    viewModel { MenuViewModel(get()) }
}