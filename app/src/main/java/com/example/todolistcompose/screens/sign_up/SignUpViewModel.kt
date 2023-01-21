package com.example.todolistcompose.screens.sign_up

import androidx.compose.runtime.mutableStateOf
import com.example.todolistcompose.common.ext.isValidEmail
import com.example.todolistcompose.common.ext.isValidPassword
import com.example.todolistcompose.common.ext.passwordMatches
import com.example.todolistcompose.common.snackbar.SnackbarManager
import com.example.todolistcompose.model.service.AccountService
import com.example.todolistcompose.model.service.LogService
import com.example.todolistcompose.navigation.SETTINGS_SCREEN
import com.example.todolistcompose.navigation.SIGN_UP_SCREEN
import com.example.todolistcompose.screens.TodoListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.todolistcompose.R.string as AppText


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: LogService
) : TodoListViewModel(logService) {

    var uiState = mutableStateOf(SignUpUiState())
        private set

    private val email
        get() = uiState.value.email

    private val passsword
        get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onRepeatPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(repeatPassword = newValue)
    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        if (!passsword.isValidPassword()) {
            SnackbarManager.showMessage(AppText.password_error)
            return
        }

        if (!passsword.passwordMatches(uiState.value.repeatPassword)) {
            SnackbarManager.showMessage(AppText.password_match_error)
            return
        }

        launchCatching {
            openAndPopUp(SETTINGS_SCREEN, SIGN_UP_SCREEN)
        }
    }


}