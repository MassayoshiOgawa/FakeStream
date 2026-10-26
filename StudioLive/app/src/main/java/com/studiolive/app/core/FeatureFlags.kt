package com.studiolive.app.core

/**
 * Feature flags - preparado para ativar/desativar funcionalidades.
 * Tudo começa desligado ou com valores padrão. Atualizar documentação ao alterar.
 */
object FeatureFlags {
    var enableLiveViewersControl: Boolean = true
    var enableComments: Boolean = true
    var enableReactions: Boolean = true
    var enableScreenRecording: Boolean = true
    var enableVolumeKeyInterception: Boolean = false // DESATIVADO POR PADRÃO - NÃO INTERCEPTA COM APP ABERTO/MINIMIZADO
    var enableConfigurableAppIcon: Boolean = true
    var enableConfigurableAppName: Boolean = true
}
