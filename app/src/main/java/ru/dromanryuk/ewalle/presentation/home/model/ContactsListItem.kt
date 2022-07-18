package ru.dromanryuk.ewalle.presentation.home.model

import ru.dromanryuk.ewalle.domain.model.user.User

sealed class ContactsListItem {
//    data class AddContactButton(val navigate: () -> Unit) : ContactsListItem()
    object AddContactButton : ContactsListItem()

    data class ContactListItem(val user: User) : ContactsListItem()
}
