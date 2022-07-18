package ru.dromanryuk.ewalle.presentation.login.model

import ru.dromanryuk.ewalle.R
import ru.dromanryuk.ewalle.domain.model.location.Weather

data class LoginState(
    val currentTime: String = "",
    val currentDate: String = "",
    val weather: Weather = Weather(34, R.drawable.weather),
    val navigateToHomeScreen: Boolean = false
)


