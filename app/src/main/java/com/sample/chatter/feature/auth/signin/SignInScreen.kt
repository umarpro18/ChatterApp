package com.sample.chatter.feature.auth.signin

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sample.chatter.R

@Composable
fun SignInScreen(
    navController: NavController
) {

    val viewModel: SignInViewModel = hiltViewModel()
    val uiState = viewModel.signInState.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val context = LocalContext.current

    LaunchedEffect(key1 = uiState.value) {
        when (uiState.value) {
            is SignInState.Error -> {
                Toast.makeText(
                    context,
                    (uiState.value as SignInState.Error).message,
                    Toast.LENGTH_LONG
                ).show()
            }

            is SignInState.Idle -> {
            }

            is SignInState.Loading -> {
            }

            is SignInState.Success -> {
                navController.navigate("home") {
                }
            }
        }
    }


    // Basically provide top bar and bottom bar (Home screen feel)
    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Here you can add your sign-in UI components
            // For example, TextFields for email and password, SignIn button, etc.
            // You can also add navigation to SignUpScreen if needed
            // Example: Button(onClick = { navController.navigate("signup") }) { Text("Sign Up") }
            // For now, we will just leave it empty

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(80.dp)
                    .background(color = Color.Yellow)
            )

            Spacer(modifier = Modifier.size(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("User Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.size(16.dp))

            if (uiState.value is SignInState.Loading) {
                CircularProgressIndicator()
            } else {
                Button(
                    onClick = { viewModel.signIn(email, password) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = email.isNotEmpty() && password.isNotEmpty() && uiState.value == SignInState.Idle || uiState.value == SignInState.Error(
                        ""
                    )
                ) {
                    Text("Sign In")
                }
            }

            TextButton(onClick = { navController.navigate("signup") }) {
                Text(text = "Don't have an account? Sign Up")
            }
        }
    }
}

@Preview
@Composable
fun SignInScreenPreview() {
    SignInScreen(navController = rememberNavController())
}