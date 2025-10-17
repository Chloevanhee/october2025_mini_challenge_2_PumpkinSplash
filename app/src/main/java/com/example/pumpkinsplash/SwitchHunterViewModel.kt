package com.example.pumpkinsplash

import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SwitchHunterViewModel : ViewModel() {
    private var _uiState = MutableStateFlow(SwitchHunterUiState(isDay = true))
    val uiState = _uiState.asStateFlow()


    fun onAction(uiEvent : HunterSwitcherEvent){
        when(uiEvent){
            is HunterSwitcherEvent.onDayModeChanged -> {
                val currentState = _uiState.value.isDay
                _uiState.value = SwitchHunterUiState(isDay = !currentState).copy()
            }
        }

    }

}

@Composable
fun SwitchHunterRoot(modifier: Modifier = Modifier){
    val viewModel: SwitchHunterViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    SwitchHunterScreen(modifier, uiState, viewModel::onAction)
}