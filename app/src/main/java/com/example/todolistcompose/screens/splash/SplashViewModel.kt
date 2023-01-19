package com.example.todolistcompose.screens.splash

import androidx.compose.runtime.mutableStateOf
import com.example.todolistcompose.model.service.AccountService
import com.example.todolistcompose.model.service.ConfigurationService
import com.example.todolistcompose.model.service.LogService
import com.example.todolistcompose.navigation.SPLASH_SCREEN
import com.example.todolistcompose.navigation.TASKS_SCREEN
import com.example.todolistcompose.screens.TodoListViewModel
import com.google.firebase.auth.FirebaseAuthException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    configurationService: ConfigurationService,
    private val accountService: AccountService,
    logService: LogService
): TodoListViewModel(logService) {

    val showError = mutableStateOf(false)

    init {
        launchCatching { configurationService.fetchConfiguration() }
    }

    fun onAppStart(openAndPopUp: (String, String) -> Unit) {
        showError.value = false
        if (accountService.hasUser) openAndPopUp(TASKS_SCREEN, SPLASH_SCREEN)
        else createAnonymousAccount(openAndPopUp)
    }

    private fun createAnonymousAccount(openAndPopUp: (String, String) -> Unit) {

        launchCatching(snackbar = false) {
            try {
                accountService.createAnonymousAccount()
            } catch (ex: FirebaseAuthException) {
                showError.value = true
                throw ex
            }
            openAndPopUp(TASKS_SCREEN, SPLASH_SCREEN)
        }
    }

}