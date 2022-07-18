package ru.dromanryuk.ewalle.presentation.home.model

import ru.dromanryuk.ewalle.domain.model.service.Service

data class HomeState(
    val user: HomeUserState? = null,
    val contacts: List<ContactsListItem> = emptyList(),
    val services: List<Service> = emptyList(),
    val navigateToMenuScreen: Boolean = false,
    val navigateToLoginScreen: Boolean = false
)


