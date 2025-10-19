package com.example.pumpkinsplash.presenter.common.animations

import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pumpkinsplash.R
import com.example.pumpkinsplash.presenter.common.constants.AnimationConstants.OFFSET_CLOUD1_X
import com.example.pumpkinsplash.presenter.common.constants.AnimationConstants.OFFSET_CLOUD1_Y
import com.example.pumpkinsplash.ui.theme.PumpkinSplashTheme

@Composable
fun Cloud1Animation(
    widthScreenPx: Int,
    transition: Transition<Boolean>,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    val offsetXInDp = with(density) { OFFSET_CLOUD1_X.toDp() }
    val offsetYInDp = with(density) { OFFSET_CLOUD1_Y.toDp() }

    val translationCloud1 by transition.animateFloat(
        transitionSpec = { tween(1000, easing = EaseIn) },
        label = "translation cloud1"
    ) { isDay -> if (!isDay) (-(widthScreenPx.toFloat())) else 0f }
    Box(
        Modifier
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(R.drawable.cloud_01),
            contentDescription = "moon",
            modifier = modifier
                .offset(offsetXInDp, offsetYInDp)
                .graphicsLayer(translationX = translationCloud1, alpha = 0.5f)

        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_6A,
    name = "Preview - Pixel 6A"
)
@Composable
fun Cloud1AnimationPreview() {
    PumpkinSplashTheme {
        val isDayModeToggle = true
        val widthScreen = 411.dp
        val widthScreenPx = 1079
        val transition = updateTransition(isDayModeToggle, label = "animation")
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(clip = false),
            contentAlignment = BottomEnd
        ) {
            Graveyard(isDayModeToggle, transition)
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(clip = false),
            contentAlignment = TopCenter
        ) {
            SunAnimation(widthScreen, transition)
            Cloud2Animation(widthScreenPx, transition)
            Cloud1Animation(411, transition, Modifier)
            Cloud3Animation(widthScreenPx, transition)
        }
    }
}
