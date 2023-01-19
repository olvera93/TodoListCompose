package com.example.todolistcompose.model.service

interface LogService {
    fun logNonFatalCrash(throwable: Throwable)
}