package ru.dromanryuk.ewalle.presentation.home.model

import ru.dromanryuk.ewalle.domain.model.user.User
import java.text.DecimalFormat

data class HomeUserState(
    val id: Int,
    val name: String,
    val balance: String,
    val contacts: List<User>?
)

fun User.toHomeUser() = HomeUserState(
    id = id,
    name = name,
    balance = formatBalance(balance),
    contacts = contacts,
)

fun formatBalance(balance: Double): String {
    val formatter = DecimalFormat("###,##0.00")
    return formatter.format(balance)
}
