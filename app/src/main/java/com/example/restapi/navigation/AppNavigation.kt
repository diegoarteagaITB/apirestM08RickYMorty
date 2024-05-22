package com.example.restapi.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.restapi.mvvm.CharactersViewModel
import com.example.restapi.ui.detailscreen.DetailScreen
import com.example.restapi.ui.listscreen.ListScreen
import com.example.restapi.ui.splashscreen.LaunchScreen

@Composable
fun AppNavigation(viewModel: CharactersViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.LaunchScreen.route){
        composable(route = AppScreens.ListScreen.route){
            ListScreen(navController,viewModel)
        }
        composable(route = AppScreens.DetailScreen.route + "/{id}"
        , arguments = listOf(navArgument(name = "id"){
            type = NavType.IntType
            })){
            DetailScreen(navController, viewModel, it.arguments?.getInt("id"))
        }
        composable(route = AppScreens.LaunchScreen.route){
            LaunchScreen(navController)
        }
    }

}
