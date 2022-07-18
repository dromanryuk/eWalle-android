package ru.dromanryuk.ewalle.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.*
import kotlinx.datetime.TimeZone
import ru.dromanryuk.ewalle.presentation.login.model.LoginEditingEvent
import ru.dromanryuk.ewalle.presentation.login.model.LoginState
import java.time.format.DateTimeFormatter
import java.util.*

class LoginViewModel : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    init {
        observeCurrentTime()
    }

    fun sendEvent(event: LoginEditingEvent) {
        when(event) {
            is LoginEditingEvent.NavigateToHomeScreen -> navigateToHomeScreen()
        }
    }

    private fun navigateToHomeScreen() {
        _state.update {
            it.copy(navigateToHomeScreen = true)
        }
    }

    private fun observeCurrentTime() = viewModelScope.launch {
        while(true) {
            val localDateTime = getCurrentLocalDateTime()
            val delay: Long = (60 - localDateTime.second.toLong()) * 1000
            setStateDateTimeText(localDateTime)
            kotlinx.coroutines.delay(delay)
        }
    }

    private fun setStateDateTimeText(localDateTime: LocalDateTime) {
        val timeFormatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH)
        val dateFormatter = DateTimeFormatter.ofPattern("MMM.d.yyyy | EEEE", Locale.ENGLISH)
        val time = localDateTime.toJavaLocalDateTime().format(timeFormatter)
        val date = localDateTime.toJavaLocalDateTime().format(dateFormatter)
        _state.update {
            it.copy(
                currentTime = time,
                currentDate = date
            )
        }
    }

    private fun getCurrentLocalDateTime(): LocalDateTime {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    }
}