package ru.dromanryuk.ewalle.navigation

import androidx.navigation.NavController
import androidx.navigation.createGraph
import androidx.navigation.fragment.fragment
import ru.dromanryuk.ewalle.presentation.home.HomeFragment
import ru.dromanryuk.ewalle.presentation.login.LoginFragment
import ru.dromanryuk.ewalle.presentation.menu.MenuFragment

fun createNavGraph(navController: NavController) {
    navController.graph = navController.createGraph(
        startDestination = NavRoutes.LOGIN
    ) {
        fragment<LoginFragment>(NavRoutes.LOGIN) {
            label = "login screen"
        }

        fragment<HomeFragment>(NavRoutes.HOME) {
            label = "home screen"
        }

        fragment<MenuFragment>(NavRoutes.MENU) {
            label = "menu screen"
        }
    }
}