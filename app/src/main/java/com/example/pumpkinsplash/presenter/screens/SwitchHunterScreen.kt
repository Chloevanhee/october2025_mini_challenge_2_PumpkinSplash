package com.example.pumpkinsplash.presenter.screens

import android.util.Log
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.pumpkinsplash.presenter.common.animations.Cloud1Animation
import com.example.pumpkinsplash.presenter.common.animations.Cloud2Animation
import com.example.pumpkinsplash.presenter.common.animations.Cloud3Animation
import com.example.pumpkinsplash.presenter.common.components.CustomToggle
import com.example.pumpkinsplash.presenter.common.animations.GhostAnimation
import com.example.pumpkinsplash.presenter.common.animations.Graveyard
import com.example.pumpkinsplash.presenter.viewmodel.HunterSwitcherEvent
import com.example.pumpkinsplash.presenter.common.animations.MoonAnimation
import com.example.pumpkinsplash.presenter.common.animations.StarAnimation
import com.example.pumpkinsplash.presenter.common.animations.SunAnimation
import com.example.pumpkinsplash.presenter.viewmodel.SwitchHunterUiState

@Composable
fun SwitchHunterScreen(
    modifier: Modifier = Modifier,
    uiState: SwitchHunterUiState,
    onAction: (HunterSwitcherEvent) -> Unit
) {

    val isDayModeToggle = uiState.isDay
    val transition = updateTransition(isDayModeToggle, label = "animation")

    val density = LocalDensity.current
    val heightScreen = LocalConfiguration.current.screenHeightDp.dp
    val widthScreen = LocalConfiguration.current.screenWidthDp.dp
    val widthScreenPx = with(density) {
        widthScreen.roundToPx()
    }
    val heightScreePx = with(density) {
        heightScreen.roundToPx()
    }
    Log.d("SwitchHunterScreen", "ScreenSize in Px: $widthScreenPx x $heightScreePx")
    Log.d("SwitchHunterScreen", "ScreenSize: $widthScreen x $heightScreen")

    Box(
        modifier = modifier
            .fillMaxSize()
            .graphicsLayer(clip = false),  // Disable clipping at root level
        contentAlignment = Alignment.Center
    ) {
        // First inner Box (no need to apply the parent modifier again)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(clip = false),
            contentAlignment = BottomEnd
        ) {
            Graveyard(isDayModeToggle, transition)
            GhostAnimation(widthScreen, heightScreen, transition)
            CustomToggle(
                isChecked = isDayModeToggle,
                onCheckedChange = { onAction(HunterSwitcherEvent.onDayModeChanged(!isDayModeToggle)) },
                modifier = Modifier.align(Alignment.BottomCenter),
                transition
            )
        }

        // Second inner Box (no need to apply the parent modifier again)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(clip = false),
            contentAlignment = TopCenter
        ) {
            SunAnimation(widthScreen, transition)
            MoonAnimation(widthScreen, transition)
            Cloud2Animation(widthScreenPx, transition)
            Cloud1Animation(widthScreenPx, transition)
            Cloud3Animation(widthScreenPx, transition)
            StarAnimation(transition)
        }
    }
}