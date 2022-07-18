package ru.dromanryuk.ewalle.navigation

import androidx.navigation.NavController

object NavRoutes {
    const val LOGIN = "login"
    const val HOME = "home"
    const val MENU = "menu"
}

fun navigateToLoginScreen(
    navController: NavController
) {
    navController.navigate(NavRoutes.LOGIN)
}

fun navigateToHomeScreen(
    navController: NavController
) {
    navController.navigate(NavRoutes.HOME)
}

fun navigateToMenuScreen(
    navController: NavController
) {
    navController.navigate(NavRoutes.MENU)
}

