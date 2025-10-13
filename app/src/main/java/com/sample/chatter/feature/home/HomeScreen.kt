package com.sample.chatter.feature.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {

    val viewModel: HomeViewModel = hiltViewModel()
    val channel = viewModel.channel.collectAsState()

    LaunchedEffect(Unit) {
        //viewModel.getChannel()
    }

    Scaffold {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Log.d("HomeScreen umarNew", "Channels: ${channel.value.size}")
            LazyColumn(modifier = Modifier.align(androidx.compose.ui.Alignment.Center)) {
                items(channel.value) { chan ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.LightGray)
                            .padding(16.dp)
                    ) {
                        Text(text = chan.name)
                    }
                }
            }
        }
    }
}