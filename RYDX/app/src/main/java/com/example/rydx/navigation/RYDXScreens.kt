package com.example.rydx.navigation

enum class RYDXScreens {
    SplashScreen,
    OpeningScreen,
    RegisterScreen,
    LoginScreen,
    OTPVerificationScreen,
    HomeScreen,
    SearchScreen,
    FilterScreen;

    companion object {
        fun fromRoute(route: String?): RYDXScreens
                = when(route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            LoginScreen.name -> LoginScreen
            OpeningScreen.name -> OpeningScreen
            RegisterScreen.name -> RegisterScreen
            OTPVerificationScreen.name -> OTPVerificationScreen
            HomeScreen.name -> HomeScreen
            SearchScreen.name -> SearchScreen
            FilterScreen.name -> FilterScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}