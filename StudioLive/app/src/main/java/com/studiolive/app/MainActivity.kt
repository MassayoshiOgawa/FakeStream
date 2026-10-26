package com.studiolive.app

import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.studiolive.app.core.VolumeKeyManager
import com.studiolive.app.ui.config.ConfigScreen
import com.studiolive.app.ui.home.HomeScreen
import com.studiolive.app.ui.live.LiveScreen
import com.studiolive.app.ui.theme.StudioLiveTheme
import com.studiolive.app.viewmodel.ConfigViewModel
import com.studiolive.app.viewmodel.LiveViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudioLiveTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    StudioLiveApp()
                }
            }
        }
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN) {
            // NÃO INTERCEPTA com app aberto conforme solicitado - estrutura preparada
            val consumed = VolumeKeyManager.instance.handleKeyEvent(event.keyCode, event)
            if (consumed) {
                return true
            }
        }
        return super.dispatchKeyEvent(event)
    }
}

@Composable
fun StudioLiveApp() {
    val navController = rememberNavController()
    val configViewModel: ConfigViewModel = viewModel()
    val liveViewModel: LiveViewModel = viewModel()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onNavigateToLive = { navController.navigate("live") },
                onNavigateToConfig = { navController.navigate("config") }
            )
        }
        composable("live") {
            LiveScreen(
                liveViewModel = liveViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable("config") {
            ConfigScreen(
                configViewModel = configViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
