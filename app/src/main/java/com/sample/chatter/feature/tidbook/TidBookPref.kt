package com.sample.chatter.feature.tidbook

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore by preferencesDataStore(name = "timer_prefs")

object TimerPreferences {
    val START_TIME = stringPreferencesKey("start_time")
    val ELAPSED_TIME = longPreferencesKey("elapsed_time")
    val IS_RUNNING = booleanPreferencesKey("is_running")
}