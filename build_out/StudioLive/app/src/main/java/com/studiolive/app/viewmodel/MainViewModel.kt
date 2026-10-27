package com.studiolive.app.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.studiolive.app.data.model.ViewerSimConfig
import com.studiolive.app.data.prefs.AppRepository
import com.studiolive.app.data.prefs.AppState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = AppRepository(application)
    
    private val _uiState = MutableStateFlow(AppState())
    val uiState: StateFlow<AppState> = _uiState.asStateFlow()
    
    private val _isSimRunning = MutableStateFlow(false)
    val isSimRunning: StateFlow<Boolean> = _isSimRunning.asStateFlow()
    
    private var simJob: Job? = null
    
    init {
        viewModelScope.launch {
            repo.appState.collect { state ->
                _uiState.value = state
                if (state.viewerSim.autoStart && !_isSimRunning.value && simJob == null) {
                    startViewerSim()
                }
            }
        }
    }
    
    fun setDisplayName(name: String) = viewModelScope.launch { repo.setDisplayName(name) }
    fun setLiveTitle(title: String) = viewModelScope.launch { repo.setLiveTitle(title) }
    fun setViewerCount(count: Int) = viewModelScope.launch { repo.setViewerCount(count) }
    fun updateViewerSim(config: ViewerSimConfig) = viewModelScope.launch { repo.updateViewerSim(config) }
    fun toggleMic() = viewModelScope.launch { repo.setMicMuted(!_uiState.value.isMicMuted) }
    fun toggleCamera() = viewModelScope.launch { repo.setCameraFront(!_uiState.value.isCameraFront) }
    
    fun startViewerSim() {
        if (_isSimRunning.value) return
        _isSimRunning.value = true
        simJob = viewModelScope.launch {
            val config = _uiState.value.viewerSim
            var current = _uiState.value.viewerCount
            if (config.startFromMin && current < config.minCount) {
                current = config.minCount
                repo.setViewerCount(current)
            }
            while (_isSimRunning.value) {
                val cfg = _uiState.value.viewerSim
                if (current >= cfg.maxCount) break
                val inc = if (cfg.randomIncrement) {
                    Random.nextInt(cfg.incrementMin.coerceAtLeast(1), cfg.incrementMax.coerceAtLeast(cfg.incrementMin) + 1)
                } else {
                    cfg.incrementBy.coerceAtLeast(1)
                }
                current = (current + inc).coerceAtMost(cfg.maxCount)
                repo.setViewerCount(current)
                val delaySec = if (cfg.randomInterval) {
                    Random.nextLong(cfg.intervalMinSeconds.coerceAtLeast(1), cfg.intervalMaxSeconds.coerceAtLeast(cfg.intervalMinSeconds) + 1)
                } else {
                    cfg.intervalSeconds.coerceAtLeast(1)
                }
                delay(delaySec * 1000L)
            }
            _isSimRunning.value = false
        }
    }
    
    fun pauseViewerSim() {
        _isSimRunning.value = false
        simJob?.cancel()
        simJob = null
    }
    
    fun resetViewerSim() {
        pauseViewerSim()
        val cfg = _uiState.value.viewerSim
        val start = if (cfg.startFromMin) cfg.minCount else 0
        viewModelScope.launch { repo.setViewerCount(start) }
    }
}
