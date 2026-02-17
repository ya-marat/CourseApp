package com.example.feature_auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor() : ViewModel() {

    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    private val _urlEvent = MutableSharedFlow<String>()

    val isLoginEnabled = combine(_email, _password) { email, password ->
        email.isNotBlank() && password.isNotBlank() && isEmailValid(email)
    }

    val urlEvent = _urlEvent.asSharedFlow()

    fun onEmailInput(email: String) {
        _email.value = email
    }

    fun onPasswordInput(password: String) {
        _password.value = password
    }

    fun onOkClick() {
        viewModelScope.launch {
            _urlEvent.emit(Consts.OK_URL)
        }
    }

    fun onVkClick() {
        viewModelScope.launch {
            _urlEvent.emit(Consts.VK_URL)
        }
    }

    private fun isEmailValid(email: String): Boolean {
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
        return regex.matches(email)
    }
}