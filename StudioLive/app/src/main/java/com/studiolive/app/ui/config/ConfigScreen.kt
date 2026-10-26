package com.studiolive.app.ui.config

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.studiolive.app.viewmodel.ConfigViewModel

@Composable
fun ConfigScreen(
    configViewModel: ConfigViewModel,
    onNavigateBack: () -> Unit
) {
    val appDisplayName by configViewModel.appDisplayName.collectAsState()
    val viewerCount by configViewModel.viewerCount.collectAsState()
    val enableComments by configViewModel.enableComments.collectAsState()
    val enableReactions by configViewModel.enableReactions.collectAsState()
    val enableScreenRecording by configViewModel.enableScreenRecording.collectAsState()
    val enableVolumeKeys by configViewModel.enableVolumeKeys.collectAsState()

    var nameInput by remember(appDisplayName) { mutableStateOf(appDisplayName) }
    var viewerInput by remember(viewerCount) { mutableStateOf(viewerCount.toString()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Configurações",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text("Geral", color = Color.White, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = nameInput,
            onValueChange = { nameInput = it },
            label = { Text("Nome exibido do app") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(Modifier.height(8.dp))
        Button(
            onClick = { configViewModel.setAppDisplayName(nameInput) },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1C1C1C))
        ) {
            Text("Salvar nome exibido", color = Color.White)
        }

        Divider(color = Color(0xFF2C2C2C), modifier = Modifier.padding(vertical = 16.dp))

        Text("Live", color = Color.White, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = viewerInput,
            onValueChange = { viewerInput = it },
            label = { Text("Quantidade de espectadores") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(Modifier.height(8.dp))
        Button(
            onClick = { 
                viewerInput.toIntOrNull()?.let { configViewModel.setViewerCount(it) }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1C1C1C))
        ) {
            Text("Salvar espectadores", color = Color.White)
        }

        Divider(color = Color(0xFF2C2C2C), modifier = Modifier.padding(vertical = 16.dp))

        Text("Funcionalidades (ligar/desligar)", color = Color.White, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Comentários", color = Color.White, modifier = Modifier.weight(1f))
            Switch(checked = enableComments, onCheckedChange = { configViewModel.toggleComments(it) })
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Reações (corações)", color = Color.White, modifier = Modifier.weight(1f))
            Switch(checked = enableReactions, onCheckedChange = { configViewModel.toggleReactions(it) })
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Gravação de tela", color = Color.White, modifier = Modifier.weight(1f))
            Switch(checked = enableScreenRecording, onCheckedChange = { configViewModel.toggleScreenRecording(it) })
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Botões de volume (PREPARADO - SEM AÇÃO)", color = Color.White, modifier = Modifier.weight(1f))
            Switch(checked = enableVolumeKeys, onCheckedChange = { configViewModel.toggleVolumeKeys(it) })
        }

        Divider(color = Color(0xFF2C2C2C), modifier = Modifier.padding(vertical = 16.dp))

        Text("Ícone do app", color = Color.White, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(4.dp))
        Text("Estrutura preparada para trocar ícone nas configurações (Activity-Aliases).", color = Color(0xFFA8A8A8), fontSize = 12.sp)
        
        Spacer(Modifier.height(24.dp))
        Button(
            onClick = onNavigateBack,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1C1C1C))
        ) {
            Text("Voltar", color = Color.White)
        }
    }
}
