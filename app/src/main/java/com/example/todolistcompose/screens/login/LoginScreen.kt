package com.example.todolistcompose.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolistcompose.common.composable.*
import com.example.todolistcompose.common.ext.basicButton
import com.example.todolistcompose.common.ext.fieldModifier
import com.example.todolistcompose.common.ext.textButton
import com.example.todolistcompose.R.string as AppText


@Composable
fun LoginScreen(
    openAndPopUp: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState

    BasicToolbar(title = AppText.login_details)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        EmailField(uiState.email, viewModel::onEmailChange, Modifier.fieldModifier())
        PasswordField(uiState.password, viewModel::onPasswordChange, Modifier.fieldModifier())

        BasicButton(text = AppText.sign_in, modifier = Modifier.basicButton()) {
            viewModel.onSignInClick(openAndPopUp)
        }

        BasicTextButton(text = AppText.forgot_password, modifier = Modifier.textButton()) {
            viewModel.onForgotPasswordClick()
        }


    }

}