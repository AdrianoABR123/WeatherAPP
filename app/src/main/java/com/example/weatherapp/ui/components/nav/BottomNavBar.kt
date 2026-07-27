package com.example.weatherapp.ui.components.nav

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.weatherapp.ui.pages.MainViewModel

@Composable
fun BottomNavBar(
    viewModel: MainViewModel,
    navController: NavHostController,
    items: List<BottomNavItem>
) {
    NavigationBar(
        containerColor = Color.White, // No M3 use containerColor para o fundo
        contentColor = Color.Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(
                    imageVector = item.icon,
                    contentDescription = item.title
                ) },
                label = { Text(text = item.title, fontSize = 12.sp) },
                alwaysShowLabel = true,
                selected = viewModel.page == item.route,
                onClick = {
                    viewModel.page = item.route
                }

            )
        }

    }
}
