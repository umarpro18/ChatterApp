package com.sample.chatter

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.sample.chatter.feature.auth.signin.SignInScreen
import com.sample.chatter.feature.auth.signup.SignUpScreen
import com.sample.chatter.feature.home.HomeScreen

@Composable
fun MainApp() {
    Surface(modifier = Modifier.fillMaxSize()) {
        // Start with a nav controller its the entry point for navigation
        // startDestination is the first screen to be shown
        // Passing navController to each screen to navigate between screens

        // Build an Expense Tracker App with Jetpack Compose & MVVM Part 1, 2, 3, 4
        // https://www.youtube.com/watch?v=Z1Yd7upQsXY (part 1)
        // Progress: Part 2 yet to start..

        val navController = rememberNavController()

        val startScreen = FirebaseAuth.getInstance().currentUser?.let {
            "home"
        } ?: "signin"
        NavHost(navController = navController, startDestination = startScreen) {
            composable("signin") {
                SignInScreen(navController)
            }

            composable("signup") {
                SignUpScreen(navController)
            }

            composable("home") {
                HomeScreen(navController)
            }
        }
    }
}