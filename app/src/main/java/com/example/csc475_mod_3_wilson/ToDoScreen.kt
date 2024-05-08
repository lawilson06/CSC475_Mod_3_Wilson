package com.example.csc475_mod_3_wilson

sealed class ToDoScreen (val route: String) {

    object ListScreen: ToDoScreen("list_screen")
    object CompletedScreen: ToDoScreen("completed_screen")
    object DetailScreen: ToDoScreen("detail_screen")


}