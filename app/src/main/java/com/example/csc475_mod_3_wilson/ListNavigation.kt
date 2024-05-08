package com.example.csc475_mod_3_wilson

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


@Composable
fun ListNavigation(viewModel: ToDoListViewModel = viewModel(),
                   navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = ToDoScreen.ListScreen.route
    ) {
        composable(ToDoScreen.ListScreen.route){
            StartView(navController = navController, viewModel)
        }

        composable(ToDoScreen.CompletedScreen.route){
            CompletedView(navController = navController, viewModel)
        }

        composable(ToDoScreen.DetailScreen.route + "/{toDoId}",
            arguments = listOf(navArgument("toDoId") {
                type = NavType.IntType
                defaultValue = 0
                nullable = false
            })){
                entry ->
                val toDoId = if (entry.arguments != null) entry.arguments!!.
                getInt("toDoId") else 0
            DetailView(toDoId = toDoId, navController = navController, viewModel = viewModel)
        }
    }
}



