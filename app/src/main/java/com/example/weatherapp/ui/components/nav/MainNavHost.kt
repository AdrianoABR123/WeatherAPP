package com.example.weatherapp.ui.components.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherapp.ui.pages.HomePage
import com.example.weatherapp.ui.pages.ListPage
import com.example.weatherapp.ui.pages.MainViewModel
import com.example.weatherapp.ui.pages.MapPage

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
){
    NavHost(
        navController,
        startDestination = Route.home
    ){
        composable<Route.home> {
            HomePage(
                modifier = modifier,
                viewModel = viewModel
            )
        }
        composable<Route.List>{
            ListPage(
                modifier = modifier,
                viewModel = viewModel
            )
        }
        composable<Route.Map>{
            MapPage(
                modifier = modifier,
                viewModel = viewModel
            )
        }
    }
}