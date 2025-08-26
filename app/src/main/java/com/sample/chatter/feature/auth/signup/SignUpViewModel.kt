package com.sample.chatter.feature.auth.signup

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow<SignUpState>(SignUpState.Idle)
    val signUpState = _state.asStateFlow()

    //https://console.firebase.google.com/project/chatter-d352f/overview
    fun signUp(name: String, email: String, password: String) {
        _state.value = SignUpState.Loading
        // Firebase Auth logic here
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { result ->
                if (result.isSuccessful) {
                    // On success
                    result.result.user?.let {
                        it.updateProfile(
                            UserProfileChangeRequest.Builder().setDisplayName(name).build()
                        )
                        _state.value = SignUpState.Success
                        return@addOnCompleteListener
                    }
                    _state.value = SignUpState.Error("SignUp failed")
                } else {
                    // On error
                    _state.value = SignUpState.Error("SignUp failed")
                }
            }
    }
}

sealed class SignUpState {
    object Idle : SignUpState()
    object Loading : SignUpState()
    data class Error(val message: String) : SignUpState()
    object Success : SignUpState()
}