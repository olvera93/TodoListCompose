package com.example.todolistcompose.model.service

interface ConfigurationService {
    suspend fun fetchConfiguration(): Boolean
    val isShowTaskEditButtonConfig: Boolean
}