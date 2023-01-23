package com.example.todolistcompose.screens.settings

import com.example.todolistcompose.model.service.AccountService
import com.example.todolistcompose.model.service.LogService
import com.example.todolistcompose.model.service.StorageService
import com.example.todolistcompose.navigation.LOGIN_SCREEN
import com.example.todolistcompose.navigation.SIGN_UP_SCREEN
import com.example.todolistcompose.navigation.SPLASH_SCREEN
import com.example.todolistcompose.screens.TodoListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    logService: LogService,
    private val accountService: AccountService,
    private val storageService: StorageService
) : TodoListViewModel(logService) {

    val uiState = accountService.currentUser.map {
        SettingsUiState(it.isAnonymous)
    }

    fun onLoginClick(openScreen: (String) -> Unit) = openScreen(LOGIN_SCREEN)

    fun onSignUpClick(openScreen: (String) -> Unit) = openScreen(SIGN_UP_SCREEN)

    fun onSignOutClick(restartApp: (String) -> Unit) {
        launchCatching {
            accountService.signOut()
            restartApp(SPLASH_SCREEN)
        }
    }

    fun onDeleteMyAccountClick(restartApp: (String) -> Unit) {
        launchCatching {
            storageService.deleteAllForUser(accountService.currentUserId)
            accountService.deleteAccount()
            restartApp(SPLASH_SCREEN)
        }
    }
}