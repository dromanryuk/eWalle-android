package ru.dromanryuk.ewalle.presentation.menu.model

data class MenuButton(
    var id: Int,
    var text: String,
    var isEnabled: Boolean = false,
    var type: MenuButtonType
)

enum class MenuButtonType {
    HOME, PROFILE, ACCOUNTS, TRANSACTIONS, STATS, SETTINGS, HELP
}