package com.example.rydx.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rydx.screens.register.RYDXRegisterScreen
import com.example.rydx.screens.RYDXSplashScreen
import com.example.rydx.screens.login.RYDXLoginScreen
import com.example.rydx.screens.opening.RYDXOpeningScreen

@Composable
fun RYDXNavigation() {
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = RYDXScreens.SplashScreen.name){

        composable(RYDXScreens.SplashScreen.name){
            RYDXSplashScreen(navController=navController)
        }
        composable(RYDXScreens.RegisterScreen.name){
           RYDXRegisterScreen(navController=navController)
        }
        composable(RYDXScreens.OpeningScreen.name){
            RYDXOpeningScreen(navController=navController)
        }
        composable(RYDXScreens.LoginScreen.name){
            RYDXLoginScreen(navController=navController)
        }

    }
}