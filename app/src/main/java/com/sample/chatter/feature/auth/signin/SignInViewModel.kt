package com.sample.chatter.feature.auth.signin

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class SignInViewModel : ViewModel() {

    private val _state = MutableStateFlow<SignInState>(SignInState.Idle)
    val state = _state.asStateFlow()

    fun signIn(email: String, password: String) {
        _state.value = SignInState.Loading
        // Firebase Auth logic here
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { result ->
                if (result.isSuccessful) {
                    // On success
                    _state.value = SignInState.Success
                } else {
                    // On error
                    _state.value = SignInState.Error("Authentication failed")
                }
            }
    }
}

sealed class SignInState {
    object Idle : SignInState()
    object Loading : SignInState()
    data class Error(val message: String) : SignInState()
    object Success : SignInState()
}