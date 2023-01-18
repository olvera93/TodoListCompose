package com.example.todolistcompose.navigation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Stable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.todolistcompose.common.snackbar.SnackbarManager

@Stable
class TodoListAppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
    private val snackbarManager: SnackbarManager

) {



}