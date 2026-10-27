package com.studiolive.app.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.studiolive.app.viewmodel.MainViewModel

@Composable
fun HomeScreen(vm: MainViewModel, onStartLive: () -> Unit, onSettings: () -> Unit) {
    val state by vm.uiState.collectAsState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("StudioLive", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Simulador de Live", color = Color(0xFFD0D0D0), fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Espectadores: ${state.viewerCount}", color = Color.White, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = onStartLive, modifier = Modifier.fillMaxWidth()) {
            Text("Iniciar AO VIVO")
        }
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedButton(onClick = onSettings, modifier = Modifier.fillMaxWidth()) {
            Text("Configurações")
        }
    }
}
