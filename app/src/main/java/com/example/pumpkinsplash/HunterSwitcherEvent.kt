package com.example.pumpkinsplash


sealed class HunterSwitcherEvent {
    data class onDayModeChanged(val isDay: Boolean) : HunterSwitcherEvent()
}
