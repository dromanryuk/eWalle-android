package ru.dromanryuk.ewalle.presentation.menu

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.dromanryuk.ewalle.presentation.menu.model.*

class MenuViewModel(private val useCases: MenuUseCases) : ViewModel() {

    private val _state = MutableStateFlow(MenuState())
    val state = _state.asStateFlow()

    init {
        getUserById()
        getNavigationButtons()
    }

    private fun getUserById() {
        val user = useCases.getUserByIdUseCase(1)
        _state.update { state ->
            state.copy(user = user.toMenuUser())
        }
    }

    private fun getNavigationButtons() {
        val buttons = listOf(
            MenuButton(1, "Home", true, MenuButtonType.HOME),
            MenuButton(2, "Profile", false, MenuButtonType.PROFILE),
            MenuButton(3, "Accounts", false, MenuButtonType.ACCOUNTS),
            MenuButton(4, "Transactions", false, MenuButtonType.TRANSACTIONS),
            MenuButton(5, "Stats", false, MenuButtonType.STATS),
            MenuButton(6,"Settings", false, MenuButtonType.SETTINGS),
            MenuButton(7, "Help", false, MenuButtonType.HELP),
        )
        _state.update { state ->
            state.copy(navigationButtons = buttons)
        }
    }

    fun sendEvent(event: MenuEditingEvent) {
        when(event) {
            MenuEditingEvent.NavigateToHomeScreen -> navigateToHomeScreen()
            MenuEditingEvent.NavigateToLoginScreen -> navigateToLoginScreen()
            is MenuEditingEvent.SelectNavigationButton -> selectNavigationButton(event.buttonId)
        }
    }

    private fun navigateToHomeScreen() {
        _state.update {
            it.copy(navigateToHomeScreen = true)
        }
    }

    private fun navigateToLoginScreen() {
        _state.update {
            it.copy(navigateToLoginScreen = true)
        }
    }

    private fun selectNavigationButton(id: Int) {
        val buttons = _state.value.navigationButtons.map { menuButton ->
            if (menuButton.id == id)
                menuButton.copy(isEnabled = true)
            else
                menuButton.copy(isEnabled = false)
        }

        _state.update {
            it.copy(navigationButtons = buttons)
        }
    }
}