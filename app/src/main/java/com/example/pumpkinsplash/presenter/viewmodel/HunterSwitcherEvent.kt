package com.example.pumpkinsplash.presenter.viewmodel

sealed class HunterSwitcherEvent {
    data class onDayModeChanged(val isDay: Boolean) : HunterSwitcherEvent()
}