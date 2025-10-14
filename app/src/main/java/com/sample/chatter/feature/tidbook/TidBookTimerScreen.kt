package com.sample.chatter.feature.tidbook

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import java.time.format.DateTimeFormatter

@Composable
fun TidBookTimerScreen(navController: NavController) {
    val timerViewModel: TimerViewModel = hiltViewModel()

    val startTime = timerViewModel.startTime.collectAsStateWithLifecycle()
    val elapsedTime = timerViewModel.elapsedTime.collectAsStateWithLifecycle()
    val isRunning = timerViewModel.isRunning.collectAsStateWithLifecycle()

    LaunchedEffect(isRunning) {
        while (true) {
            delay(1000L)
            timerViewModel.tick()
        }
    }

    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Date and elapsed
        Text(
            "Date: ${startTime.value?.toLocalDate() ?: java.time.LocalDate.now()}",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            "Elapsed: ${(elapsedTime.value / 3600)}:${((elapsedTime.value % 3600) / 60)}:${(elapsedTime.value % 60)}",
            style = MaterialTheme.typography.titleLarge
        )

        // Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Start (big green)
            Button(
                onClick = { timerViewModel.startTimer() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .weight(2f)
                    .height(56.dp)
            ) {
                Text("Start")
            }

            // Stop (big red)
            Button(
                onClick = { timerViewModel.stopTimer() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336)),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .weight(2f)
                    .height(56.dp)
            ) {
                Text("Stop")
            }
        }

        // Secondary buttons (Pause, Resume)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = { timerViewModel.pauseTimer() },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
            ) {
                Text("Pause")
            }

            Button(
                onClick = { timerViewModel.resumeTimer() },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
            ) {
                Text("Resume")
            }
        }

        // Start time display
        Text(
            "Start Time: ${startTime.value?.toLocalTime()?.format(formatter) ?: "--:--:--"}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TidBookTimerScreenPreview() {
    TidBookTimerScreen(navController = rememberNavController())
}