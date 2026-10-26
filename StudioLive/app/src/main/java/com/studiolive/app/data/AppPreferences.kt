package com.studiolive.app.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "studiolive_prefs")

class AppPreferences(private val context: Context) {
    companion object {
        val APP_DISPLAY_NAME = stringPreferencesKey("app_display_name")
        val VIEWER_COUNT = intPreferencesKey("viewer_count")
        val ENABLE_COMMENTS = booleanPreferencesKey("enable_comments")
        val ENABLE_REACTIONS = booleanPreferencesKey("enable_reactions")
        val ENABLE_SCREEN_RECORDING = booleanPreferencesKey("enable_screen_recording")
        val ENABLE_VOLUME_KEYS = booleanPreferencesKey("enable_volume_keys")
        val APP_ICON_NAME = stringPreferencesKey("app_icon_name")
    }

    val appDisplayName: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[APP_DISPLAY_NAME] ?: "StudioLive"
    }

    val viewerCount: Flow<Int> = context.dataStore.data.map { prefs ->
        prefs[VIEWER_COUNT] ?: 0
    }

    val enableComments: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[ENABLE_COMMENTS] ?: true
    }

    val enableReactions: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[ENABLE_REACTIONS] ?: true
    }

    val enableScreenRecording: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[ENABLE_SCREEN_RECORDING] ?: true
    }

    val enableVolumeKeys: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[ENABLE_VOLUME_KEYS] ?: false
    }

    val appIconName: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[APP_ICON_NAME] ?: "default"
    }

    suspend fun setAppDisplayName(name: String) {
        context.dataStore.edit { it[APP_DISPLAY_NAME] = name }
    }

    suspend fun setViewerCount(count: Int) {
        context.dataStore.edit { it[VIEWER_COUNT] = if (count < 0) 0 else count }
    }

    suspend fun setEnableComments(enabled: Boolean) {
        context.dataStore.edit { it[ENABLE_COMMENTS] = enabled }
    }

    suspend fun setEnableReactions(enabled: Boolean) {
        context.dataStore.edit { it[ENABLE_REACTIONS] = enabled }
    }

    suspend fun setEnableScreenRecording(enabled: Boolean) {
        context.dataStore.edit { it[ENABLE_SCREEN_RECORDING] = enabled }
    }

    suspend fun setEnableVolumeKeys(enabled: Boolean) {
        context.dataStore.edit { it[ENABLE_VOLUME_KEYS] = enabled }
    }

    suspend fun setAppIconName(name: String) {
        context.dataStore.edit { it[APP_ICON_NAME] = name }
    }
}
