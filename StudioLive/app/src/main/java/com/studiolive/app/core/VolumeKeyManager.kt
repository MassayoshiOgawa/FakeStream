package com.studiolive.app.core

import android.view.KeyEvent

/**
 * VolumeKeyManager - ESTRUTURA PREPARADA PARA MEGA FUNCIONALIDADE FUTURA
 * 
 * REGRAS:
 * - NÃO DEVE INTERCEPTAR BOTÕES DE VOLUME ENQUANTO O APP ESTIVER ABERTO (FOREGROUND)
 * - NÃO DEVE INTERCEPTAR ENQUANTO O APP ESTIVER MINIMIZADO/EM BACKGROUND (RECENT APPS)
 * - TODA LÓGICA FICARÁ VAZIA ATÉ QUE SEJA IMPLEMENTADA
 * - USAR INTERFACES PARA PLUGAR FUNCIONALIDADE DEPOIS SEM REFATORAR
 */
interface IVolumeKeyAction {
    fun onVolumeUp(): Boolean // retornar true se consumir evento
    fun onVolumeDown(): Boolean // retornar true se consumir evento
}

class VolumeKeyManager {
    private var action: IVolumeKeyAction? = null
    
    fun setAction(action: IVolumeKeyAction?) {
        this.action = action
    }
    
    fun handleKeyEvent(keyCode: Int, event: KeyEvent): Boolean {
        if (!FeatureFlags.enableVolumeKeyInterception) {
            return false // SEMPRE NÃO CONSUME POR PADRÃO
        }
        
        // APP ABERTO/MINIMIZADO - NÃO INTERCEPTAR CONFORME SOLICITADO
        // Estrutura preparada para lógica futura
        return when (keyCode) {
            KeyEvent.KEYCODE_VOLUME_UP -> {
                // TODO: Implementar quando solicitado
                action?.onVolumeUp() ?: false
            }
            KeyEvent.KEYCODE_VOLUME_DOWN -> {
                // TODO: Implementar quando solicitado
                action?.onVolumeDown() ?: false
            }
            else -> false
        }
    }
    
    companion object {
        val instance = VolumeKeyManager()
    }
}
