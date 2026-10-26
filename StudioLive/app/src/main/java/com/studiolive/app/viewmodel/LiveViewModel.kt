package com.studiolive.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.studiolive.app.data.AppPreferences
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class LiveViewModel(application: Application) : AndroidViewModel(application) {
    private val prefs = AppPreferences(application)

    private val _viewerCount = MutableStateFlow(0)
    val viewerCount: StateFlow<Int> = _viewerCount.asStateFlow()

    private val _elapsedSeconds = MutableStateFlow(0L)
    val elapsedSeconds: StateFlow<Long> = _elapsedSeconds.asStateFlow()

    private val _isLive = MutableStateFlow(false)
    val isLive: StateFlow<Boolean> = _isLive.asStateFlow()

    init {
        viewModelScope.launch {
            prefs.viewerCount.collect { _viewerCount.value = it }
        }
    }

    fun startLive() {
        if (_isLive.value) return
        _isLive.value = true
        _elapsedSeconds.value = 0L
        viewModelScope.launch {
            while (_isLive.value) {
                delay(1000L)
                _elapsedSeconds.value += 1L
            }
        }
    }

    fun stopLive() {
        _isLive.value = false
    }

    fun setViewerCount(count: Int) {
        val c = if (count < 0) 0 else count
        _viewerCount.value = c
        viewModelScope.launch {
            prefs.setViewerCount(c)
        }
    }

    fun incrementViewer() {
        setViewerCount(_viewerCount.value + 1)
    }

    fun decrementViewer() {
        setViewerCount(_viewerCount.value - 1)
    }

    fun formatElapsed(): String {
        val total = _elapsedSeconds.value
        val minutes = TimeUnit.SECONDS.toMinutes(total)
        val seconds = total - TimeUnit.MINUTES.toSeconds(minutes)
        return String.format("%02d:%02d", minutes, seconds)
    }
}
