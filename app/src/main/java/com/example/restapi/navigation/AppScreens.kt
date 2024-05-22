package com.example.restapi.navigation

sealed class AppScreens(val route: String){

    object LaunchScreen : AppScreens("launch_screen")

    object ListScreen : AppScreens("list_screen")

    object  DetailScreen : AppScreens("detail_screen")

}