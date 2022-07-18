package ru.dromanryuk.ewalle.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.dromanryuk.ewalle.presentation.home.model.ContactsListItem
import ru.dromanryuk.ewalle.presentation.home.model.HomeEditingEvent
import ru.dromanryuk.ewalle.presentation.home.model.HomeState
import ru.dromanryuk.ewalle.presentation.home.model.toHomeUser

class HomeViewModel(private val useCases: HomeUseCases) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        getUserById()
        getUserContacts()
        getAllServices()
    }

    private fun getUserById() {
        val user = useCases.getUserByIdUseCase(1)
        _state.update { state ->
            state.copy(user = user.toHomeUser())
        }
    }

    private fun getUserContacts() = viewModelScope.launch {
        _state.value.user?.let { user ->
            user.contacts?.let {
                val contacts = mutableListOf<ContactsListItem>()
                it.map { contacts.add(ContactsListItem.ContactListItem(it))  }
                contacts.add(0, ContactsListItem.AddContactButton)

                _state.update { state ->
                    state.copy(contacts = contacts)
                }
            }
        }
    }

    private fun getAllServices() {
        val services = useCases.getAllServicesUseCase()
        _state.update { state ->
            state.copy(services = services)
        }
    }

    fun sendEvent(event: HomeEditingEvent) {
        when(event) {
            HomeEditingEvent.NavigateToMenuScreen -> navigateToMenuScreen()
            HomeEditingEvent.NavigateToLoginScreen -> navigateToLoginScreen()
        }
    }

    private fun navigateToMenuScreen() {
        _state.update {
            it.copy(navigateToMenuScreen = true)
        }
    }

    private fun navigateToLoginScreen() {
        _state.update {
            it.copy(navigateToLoginScreen = true)
        }
    }
}