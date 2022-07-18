package ru.dromanryuk.ewalle.presentation.menu.model

sealed class MenuEditingEvent {
    object NavigateToHomeScreen : MenuEditingEvent()

    object NavigateToLoginScreen : MenuEditingEvent()

    data class SelectNavigationButton(val buttonId: Int) : MenuEditingEvent()
}
