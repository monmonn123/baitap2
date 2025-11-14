package com.example.baitap_bt2

sealed class Screen(val route: String) {
    object IntroScreen : Screen("home")
    object Register : Screen("register")
    object Login : Screen("login")
    object HomeScreen: Screen("home_screen")
}
