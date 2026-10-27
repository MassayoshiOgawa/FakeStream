package com.studiolive.app.data.model

data class ViewerSimConfig(
    val enabled: Boolean = true,
    val minCount: Int = 0,
    val maxCount: Int = 1500,
    val incrementBy: Int = 1,
    val intervalSeconds: Long = 3L,
    val autoStart: Boolean = false,
    val startFromMin: Boolean = true,
    val randomInterval: Boolean = false,
    val intervalMinSeconds: Long = 2L,
    val intervalMaxSeconds: Long = 5L,
    val randomIncrement: Boolean = false,
    val incrementMin: Int = 1,
    val incrementMax: Int = 3
)
