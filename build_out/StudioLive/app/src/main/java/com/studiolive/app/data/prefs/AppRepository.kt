package com.studiolive.app.data.prefs

import android.content.Context
import com.studiolive.app.data.model.ViewerSimConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppRepository(private val context: Context) {
    
    val appState: Flow<AppState> = context.dataStore.data.map { prefs ->
        AppState(
            displayName = prefs[PrefKeys.DISPLAY_NAME] ?: "guia.do.celular",
            liveTitle = prefs[PrefKeys.LIVE_TITLE] ?: "youngezee",
            avatarPath = prefs[PrefKeys.AVATAR_PATH] ?: "",
            viewerCount = prefs[PrefKeys.VIEWER_COUNT] ?: 0,
            viewerSim = ViewerSimConfig(
                enabled = prefs[PrefKeys.VSIM_ENABLED] ?: true,
                minCount = prefs[PrefKeys.VSIM_MIN] ?: 0,
                maxCount = prefs[PrefKeys.VSIM_MAX] ?: 1500,
                incrementBy = prefs[PrefKeys.VSIM_INC] ?: 1,
                intervalSeconds = prefs[PrefKeys.VSIM_INTERVAL] ?: 3L,
                autoStart = prefs[PrefKeys.VSIM_AUTOSTART] ?: false,
                startFromMin = prefs[PrefKeys.VSIM_START_FROM_MIN] ?: true,
                randomInterval = prefs[PrefKeys.VSIM_RANDOM_INTERVAL] ?: false,
                intervalMinSeconds = prefs[PrefKeys.VSIM_INTERVAL_MIN] ?: 2L,
                intervalMaxSeconds = prefs[PrefKeys.VSIM_INTERVAL_MAX] ?: 5L,
                randomIncrement = prefs[PrefKeys.VSIM_RANDOM_INC] ?: false,
                incrementMin = prefs[PrefKeys.VSIM_INC_MIN] ?: 1,
                incrementMax = prefs[PrefKeys.VSIM_INC_MAX] ?: 3
            ),
            isMicMuted = prefs[PrefKeys.MIC_MUTED] ?: false,
            isCameraFront = prefs[PrefKeys.CAMERA_FRONT] ?: false
        )
    }
    
    suspend fun setDisplayName(value: String) {
        context.dataStore.updateData { it.toMutablePreferences().apply { this[PrefKeys.DISPLAY_NAME] = value.trim().take(30) } }
    }
    
    suspend fun setLiveTitle(value: String) {
        context.dataStore.updateData { it.toMutablePreferences().apply { this[PrefKeys.LIVE_TITLE] = value.trim().take(60) } }
    }
    
    suspend fun setViewerCount(value: Int) {
        context.dataStore.updateData { it.toMutablePreferences().apply { this[PrefKeys.VIEWER_COUNT] = value.coerceIn(0, 9999999) } }
    }
    
    suspend fun updateViewerSim(config: ViewerSimConfig) {
        context.dataStore.updateData { prefs ->
            prefs.toMutablePreferences().apply {
                this[PrefKeys.VSIM_ENABLED] = config.enabled
                this[PrefKeys.VSIM_MIN] = config.minCount
                this[PrefKeys.VSIM_MAX] = config.maxCount
                this[PrefKeys.VSIM_INC] = config.incrementBy
                this[PrefKeys.VSIM_INTERVAL] = config.intervalSeconds
                this[PrefKeys.VSIM_AUTOSTART] = config.autoStart
                this[PrefKeys.VSIM_START_FROM_MIN] = config.startFromMin
                this[PrefKeys.VSIM_RANDOM_INTERVAL] = config.randomInterval
                this[PrefKeys.VSIM_INTERVAL_MIN] = config.intervalMinSeconds
                this[PrefKeys.VSIM_INTERVAL_MAX] = config.intervalMaxSeconds
                this[PrefKeys.VSIM_RANDOM_INC] = config.randomIncrement
                this[PrefKeys.VSIM_INC_MIN] = config.incrementMin
                this[PrefKeys.VSIM_INC_MAX] = config.incrementMax
            }
        }
    }
    
    suspend fun setMicMuted(muted: Boolean) {
        context.dataStore.updateData { it.toMutablePreferences().apply { this[PrefKeys.MIC_MUTED] = muted } }
    }
    
    suspend fun setCameraFront(front: Boolean) {
        context.dataStore.updateData { it.toMutablePreferences().apply { this[PrefKeys.CAMERA_FRONT] = front } }
    }
}

data class AppState(
    val displayName: String = "guia.do.celular",
    val liveTitle: String = "youngezee",
    val avatarPath: String = "",
    val viewerCount: Int = 0,
    val viewerSim: ViewerSimConfig = ViewerSimConfig(),
    val isMicMuted: Boolean = false,
    val isCameraFront: Boolean = false
)
