package com.studiolive.app.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.studiolive.app.viewmodel.MainViewModel

@Composable
fun SettingsScreen(vm: MainViewModel, onBack: () -> Unit, onViewerSim: () -> Unit) {
    val state by vm.uiState.collectAsState()
    var displayName by remember(state.displayName) { mutableStateOf(state.displayName) }
    var liveTitle by remember(state.liveTitle) { mutableStateOf(state.liveTitle) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar", tint = Color.White)
            }
            Text("Configurações", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text("Perfil e informações", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = displayName,
                onValueChange = { displayName = it; vm.setDisplayName(it) },
                label = { Text("Nome exibido no app") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = liveTitle,
                onValueChange = { liveTitle = it; vm.setLiveTitle(it) },
                label = { Text("Título da transmissão") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))
            
            Text("Simulador de espectadores", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onViewerSim() }
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Regulagem de espectadores", color = Color.White, fontSize = 15.sp)
                    Text("Mínimo/máximo, intervalo, incremento", color = Color(0xFFD0D0D0), fontSize = 13.sp)
                }
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = Color.White)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("Atual: ${state.viewerCount} espectadores", color = Color(0xFFD0D0D0), fontSize = 13.sp)
        }
    }
}
