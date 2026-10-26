package com.studiolive.app.ui.live.overlay

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Escala responsiva para adaptar o overlay ao Motorola G84 5G (2400x1080 20:9)
 * Baseado em largura de referência 360dp (padrão usado no spec Instagram)
 */
object LiveOverlayScale {
    private const val REFERENCE_WIDTH_DP = 360f
    
    fun scale(screenWidthDp: Float): Float {
        if (screenWidthDp <= 0f) return 1f
        val factor = screenWidthDp / REFERENCE_WIDTH_DP
        // Limitar para evitar ficar muito grande/pequeno em extremos
        return factor.coerceIn(0.9f, 1.25f)
    }
    
    fun dp(value: Float, screenWidthDp: Float): Dp {
        return (value * scale(screenWidthDp)).dp
    }
    
    fun sp(value: Float, screenWidthDp: Float): Float {
        return value * scale(screenWidthDp)
    }
}
