package com.studiolive.app.data.prefs

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PrefKeys {
    val DISPLAY_NAME = stringPreferencesKey("display_name")
    val LIVE_TITLE = stringPreferencesKey("live_title")
    val AVATAR_PATH = stringPreferencesKey("avatar_path")
    val VIEWER_COUNT = intPreferencesKey("viewer_count")
    
    val VSIM_ENABLED = booleanPreferencesKey("vsim_enabled")
    val VSIM_MIN = intPreferencesKey("vsim_min")
    val VSIM_MAX = intPreferencesKey("vsim_max")
    val VSIM_INC = intPreferencesKey("vsim_inc")
    val VSIM_INTERVAL = longPreferencesKey("vsim_interval")
    val VSIM_AUTOSTART = booleanPreferencesKey("vsim_autostart")
    val VSIM_START_FROM_MIN = booleanPreferencesKey("vsim_start_from_min")
    val VSIM_RANDOM_INTERVAL = booleanPreferencesKey("vsim_random_interval")
    val VSIM_INTERVAL_MIN = longPreferencesKey("vsim_interval_min")
    val VSIM_INTERVAL_MAX = longPreferencesKey("vsim_interval_max")
    val VSIM_RANDOM_INC = booleanPreferencesKey("vsim_random_inc")
    val VSIM_INC_MIN = intPreferencesKey("vsim_inc_min")
    val VSIM_INC_MAX = intPreferencesKey("vsim_inc_max")
    
    val MIC_MUTED = booleanPreferencesKey("mic_muted")
    val CAMERA_FRONT = booleanPreferencesKey("camera_front")
}
