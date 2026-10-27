package com.studiolive.app.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.studiolive.app.ui.home.HomeScreen
import com.studiolive.app.ui.live.LiveScreen
import com.studiolive.app.ui.permissions.PermissionsScreen
import com.studiolive.app.ui.settings.SettingsScreen
import com.studiolive.app.ui.settings.viewers.ViewerSimulatorScreen
import com.studiolive.app.viewmodel.MainViewModel

sealed class Screen(val route: String) {
    data object Permissions : Screen("permissions")
    data object Home : Screen("home")
    data object Live : Screen("live")
    data object Settings : Screen("settings")
    data object ViewerSimulator : Screen("viewer_simulator")
}

@Composable
fun NavGraph(navController: NavHostController, vm: MainViewModel = viewModel()) {
    NavHost(navController = navController, startDestination = Screen.Permissions.route) {
        composable(Screen.Permissions.route) {
            PermissionsScreen(
                onContinue = { navController.navigate(Screen.Home.route) { popUpTo(Screen.Permissions.route) { inclusive = true } } }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                vm = vm,
                onStartLive = { navController.navigate(Screen.Live.route) },
                onSettings = { navController.navigate(Screen.Settings.route) }
            )
        }
        composable(Screen.Live.route) {
            LiveScreen(
                vm = vm,
                onExit = { navController.popBackStack(Screen.Home.route, false) }
            )
        }
        composable(Screen.Settings.route) {
            SettingsScreen(
                vm = vm,
                onBack = { navController.popBackStack() },
                onViewerSim = { navController.navigate(Screen.ViewerSimulator.route) }
            )
        }
        composable(Screen.ViewerSimulator.route) {
            ViewerSimulatorScreen(
                vm = vm,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
