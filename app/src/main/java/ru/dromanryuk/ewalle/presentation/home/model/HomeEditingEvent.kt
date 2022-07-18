package ru.dromanryuk.ewalle.presentation.home.model

sealed class HomeEditingEvent {
    object NavigateToMenuScreen : HomeEditingEvent()

    object NavigateToLoginScreen : HomeEditingEvent()
}
