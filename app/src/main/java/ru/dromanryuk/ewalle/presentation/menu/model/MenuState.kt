package ru.dromanryuk.ewalle.presentation.menu.model

data class MenuState(
    val user: MenuUserState? = null,
    val navigateToHomeScreen: Boolean = false,
    val navigateToLoginScreen: Boolean = false,
    val navigationButtons: List<MenuButton> = emptyList()
)


