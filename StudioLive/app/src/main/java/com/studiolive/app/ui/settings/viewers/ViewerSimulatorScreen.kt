package com.studiolive.app.ui.settings.viewers

import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
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
import com.studiolive.app.data.model.ViewerSimConfig
import com.studiolive.app.viewmodel.MainViewModel

@Composable
fun ViewerSimulatorScreen(vm: MainViewModel, onBack: () -> Unit) {
    val state by vm.uiState.collectAsState()
    val isRunning by vm.isSimRunning.collectAsState()
    val cfg = state.viewerSim
    
    var enabled by remember(cfg.enabled) { mutableStateOf(cfg.enabled) }
    var minCount by remember(cfg.minCount) { mutableStateOf(cfg.minCount.toString()) }
    var maxCount by remember(cfg.maxCount) { mutableStateOf(cfg.maxCount.toString()) }
    var incrementBy by remember(cfg.incrementBy) { mutableStateOf(cfg.incrementBy.toString()) }
    var intervalSeconds by remember(cfg.intervalSeconds) { mutableStateOf(cfg.intervalSeconds.toString()) }
    var autoStart by remember(cfg.autoStart) { mutableStateOf(cfg.autoStart) }
    var startFromMin by remember(cfg.startFromMin) { mutableStateOf(cfg.startFromMin) }
    var randomInterval by remember(cfg.randomInterval) { mutableStateOf(cfg.randomInterval) }
    var intervalMin by remember(cfg.intervalMinSeconds) { mutableStateOf(cfg.intervalMinSeconds.toString()) }
    var intervalMax by remember(cfg.intervalMaxSeconds) { mutableStateOf(cfg.intervalMaxSeconds.toString()) }
    var randomIncrement by remember(cfg.randomIncrement) { mutableStateOf(cfg.randomIncrement) }
    var incMin by remember(cfg.incrementMin) { mutableStateOf(cfg.incrementMin.toString()) }
    var incMax by remember(cfg.incrementMax) { mutableStateOf(cfg.incrementMax.toString()) }
    
    fun save() {
        val newCfg = ViewerSimConfig(
            enabled = enabled,
            minCount = minCount.toIntOrNull() ?: cfg.minCount,
            maxCount = maxCount.toIntOrNull() ?: cfg.maxCount,
            incrementBy = incrementBy.toIntOrNull() ?: cfg.incrementBy,
            intervalSeconds = intervalSeconds.toLongOrNull() ?: cfg.intervalSeconds,
            autoStart = autoStart,
            startFromMin = startFromMin,
            randomInterval = randomInterval,
            intervalMinSeconds = intervalMin.toLongOrNull() ?: cfg.intervalMinSeconds,
            intervalMaxSeconds = intervalMax.toLongOrNull() ?: cfg.intervalMaxSeconds,
            randomIncrement = randomIncrement,
            incrementMin = incMin.toIntOrNull() ?: cfg.incrementMin,
            incrementMax = incMax.toIntOrNull() ?: cfg.incrementMax
        )
        vm.updateViewerSim(newCfg)
    }
    
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
            Text("Simulador de espectadores", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text("Espectadores atuais: ${state.viewerCount}", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Ativar simulador", color = Color.White, modifier = Modifier.weight(1f))
                Switch(checked = enabled, onCheckedChange = { enabled = it; save() })
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Iniciar automaticamente", color = Color.White, modifier = Modifier.weight(1f))
                Switch(checked = autoStart, onCheckedChange = { autoStart = it; save() })
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Iniciar a partir do mínimo", color = Color.White, modifier = Modifier.weight(1f))
                Switch(checked = startFromMin, onCheckedChange = { startFromMin = it; save() })
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            Text("Limites", color = Color.White, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                OutlinedTextField(value = minCount, onValueChange = { minCount = it }, label = { Text("Mínimo") }, modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.padding(4.dp))
                OutlinedTextField(value = maxCount, onValueChange = { maxCount = it }, label = { Text("Máximo") }, modifier = Modifier.weight(1f))
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            Text("Incremento por vez", color = Color.White, fontWeight = FontWeight.SemiBold)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Aleatório por incremento", color = Color.White, modifier = Modifier.weight(1f))
                Switch(checked = randomIncrement, onCheckedChange = { randomIncrement = it; save() })
            }
            if (randomIncrement) {
                Row {
                    OutlinedTextField(value = incMin, onValueChange = { incMin = it }, label = { Text("Incremento mín.") }, modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.padding(4.dp))
                    OutlinedTextField(value = incMax, onValueChange = { incMax = it }, label = { Text("Incremento máx.") }, modifier = Modifier.weight(1f))
                }
            } else {
                OutlinedTextField(value = incrementBy, onValueChange = { incrementBy = it }, label = { Text("Quantidade por incremento") }, modifier = Modifier.fillMaxWidth())
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            Text("Intervalo entre entradas", color = Color.White, fontWeight = FontWeight.SemiBold)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Intervalo aleatório", color = Color.White, modifier = Modifier.weight(1f))
                Switch(checked = randomInterval, onCheckedChange = { randomInterval = it; save() })
            }
            if (randomInterval) {
                Row {
                    OutlinedTextField(value = intervalMin, onValueChange = { intervalMin = it }, label = { Text("Intervalo mín. (seg)") }, modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.padding(4.dp))
                    OutlinedTextField(value = intervalMax, onValueChange = { intervalMax = it }, label = { Text("Intervalo máx. (seg)") }, modifier = Modifier.weight(1f))
                }
            } else {
                OutlinedTextField(value = intervalSeconds, onValueChange = { intervalSeconds = it }, label = { Text("Intervalo (segundos)") }, modifier = Modifier.fillMaxWidth())
            }
            
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { save(); if (isRunning) vm.pauseViewerSim() else vm.startViewerSim() }, modifier = Modifier.fillMaxWidth()) {
                Text(if (isRunning) "Pausar" else "Iniciar")
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(onClick = { save(); vm.pauseViewerSim() }, modifier = Modifier.fillMaxWidth()) {
                Text("Parar")
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(onClick = { save(); vm.resetViewerSim() }, modifier = Modifier.fillMaxWidth()) {
                Text("Zerar")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Tudo é salvo automaticamente ao alterar.", color = Color(0xFFD0D0D0), fontSize = 12.sp)
        }
    }
}
