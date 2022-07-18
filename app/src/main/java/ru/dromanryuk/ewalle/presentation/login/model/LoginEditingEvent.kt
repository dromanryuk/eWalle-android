package ru.dromanryuk.ewalle.presentation.login.model

sealed class LoginEditingEvent {
    object NavigateToHomeScreen : LoginEditingEvent()
}
