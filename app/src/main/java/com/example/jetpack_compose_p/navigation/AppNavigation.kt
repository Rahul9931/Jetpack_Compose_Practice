package com.example.jetpack_compose_p.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpack_compose_p.components.QrScannerScreen
import com.example.jetpack_compose_p.screen.MainScreen

// AppNavigation.kt
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(
                onSearchClick = { /* Handle search click */ },
                onQrScannerClick = { navController.navigate("scanner") }
            )
        }
        composable("scanner") {
            QrScannerScreen(
                onScanComplete = { result ->
                    // Handle the scanned result
                    println("Scanned result: $result")
                   // navController.popBackStack()
                },
                onBack = { navController.popBackStack() }
            )
        }
    }
}