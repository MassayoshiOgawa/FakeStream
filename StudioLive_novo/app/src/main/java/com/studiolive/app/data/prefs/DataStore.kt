package com.studiolive.app.data.prefs

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore by preferencesDataStore(name = "studiolive_prefs")
