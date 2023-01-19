package com.example.todolistcompose.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistcompose.common.snackbar.SnackbarManager
import com.example.todolistcompose.common.snackbar.SnackbarMessage.Companion.toSnackbarMessage
import com.example.todolistcompose.model.service.LogService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class TodoListViewModel(
    private val logService: LogService
): ViewModel() {

    fun launchCatching(snackbar: Boolean = true, block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                if (snackbar) {
                    SnackbarManager.showMessage(throwable.toSnackbarMessage())
                }
                logService.logNonFatalCrash(throwable)
            },
            block = block
        )
}