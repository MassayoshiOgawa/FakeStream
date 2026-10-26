package com.studiolive.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.studiolive.app.core.FeatureFlags
import com.studiolive.app.data.AppPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ConfigViewModel(application: Application) : AndroidViewModel(application) {
    private val prefs = AppPreferences(application)

    private val _appDisplayName = MutableStateFlow("StudioLive")
    val appDisplayName: StateFlow<String> = _appDisplayName.asStateFlow()

    private val _viewerCount = MutableStateFlow(0)
    val viewerCount: StateFlow<Int> = _viewerCount.asStateFlow()

    private val _enableComments = MutableStateFlow(true)
    val enableComments: StateFlow<Boolean> = _enableComments.asStateFlow()

    private val _enableReactions = MutableStateFlow(true)
    val enableReactions: StateFlow<Boolean> = _enableReactions.asStateFlow()

    private val _enableScreenRecording = MutableStateFlow(true)
    val enableScreenRecording: StateFlow<Boolean> = _enableScreenRecording.asStateFlow()

    private val _enableVolumeKeys = MutableStateFlow(false)
    val enableVolumeKeys: StateFlow<Boolean> = _enableVolumeKeys.asStateFlow()

    init {
        viewModelScope.launch {
            prefs.appDisplayName.collect { _appDisplayName.value = it }
        }
        viewModelScope.launch {
            prefs.viewerCount.collect { _viewerCount.value = it }
        }
        viewModelScope.launch {
            prefs.enableComments.collect { _enableComments.value = it }
        }
        viewModelScope.launch {
            prefs.enableReactions.collect { _enableReactions.value = it }
        }
        viewModelScope.launch {
            prefs.enableScreenRecording.collect { _enableScreenRecording.value = it }
        }
        viewModelScope.launch {
            prefs.enableVolumeKeys.collect { _enableVolumeKeys.value = it }
        }
    }

    fun setAppDisplayName(name: String) {
        viewModelScope.launch {
            prefs.setAppDisplayName(name.ifBlank { "StudioLive" })
        }
    }

    fun setViewerCount(count: Int) {
        viewModelScope.launch {
            prefs.setViewerCount(count)
        }
    }

    fun toggleComments(enabled: Boolean) {
        viewModelScope.launch {
            prefs.setEnableComments(enabled)
            FeatureFlags.enableComments = enabled
        }
    }

    fun toggleReactions(enabled: Boolean) {
        viewModelScope.launch {
            prefs.setEnableReactions(enabled)
            FeatureFlags.enableReactions = enabled
        }
    }

    fun toggleScreenRecording(enabled: Boolean) {
        viewModelScope.launch {
            prefs.setEnableScreenRecording(enabled)
            FeatureFlags.enableScreenRecording = enabled
        }
    }

    fun toggleVolumeKeys(enabled: Boolean) {
        viewModelScope.launch {
            prefs.setEnableVolumeKeys(enabled)
            FeatureFlags.enableVolumeKeyInterception = enabled
        }
    }
}
