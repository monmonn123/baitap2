package com.example.baitap_bt2

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
// BƯỚC 1: Xóa hoàn toàn tham số ViewModel khỏi đây
fun Navigation(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.IntroScreen.route
    ) {
        composable(Screen.IntroScreen.route) {
            // Các màn hình này không cần nhận ViewModel từ đây
            IntroScreen(navController)
        }
        composable(Screen.Register.route) {
            RegisterScreen(navController)
        }
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(Screen.HomeScreen.route) {
            DashboardScreen()
        }
    }
}
