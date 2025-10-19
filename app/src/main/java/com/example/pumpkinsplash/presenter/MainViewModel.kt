package com.example.pumpkinsplash.presenter

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    companion object {
        private const val TAG = "PumpkinSplash_VM"
    }

    var isReady by mutableStateOf(false)
        private set

    init {
        Log.d(TAG, "🎃 MainViewModel initialized - starting")

        viewModelScope.launch {
            Log.d(TAG, "🎃 Starting splash delay coroutine...")
            delay(800L)
            isReady = true
            Log.d(TAG, "🎃 MainViewModel ready state changed: isReady = $isReady")
        }
    }
}