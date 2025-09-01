package com.example.recipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.recipeapp.ui.theme.MainViewModel

@Composable
fun RecipeApp(navController: NavHostController) {
    val recipeViewModel: MainViewModel = viewModel()
    val viewState by recipeViewModel.categoriesState

    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route) {
        composable(Screen.RecipeScreen.route) {
            RecipeScreen(viewState = viewState,
                navigateToDetails = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("cat", it)
                    navController.navigate(Screen.DetailScreen.route)
                })
        }
        composable(Screen.DetailScreen.route) {
            val category = navController.previousBackStackEntry?.savedStateHandle?.
            get<Category>("cat")?: Category("","","","")
            CategoryDetailsScreen(category)
        }
    }
}