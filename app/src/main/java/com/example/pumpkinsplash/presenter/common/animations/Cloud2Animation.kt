package com.example.pumpkinsplash.presenter.common.animations

import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.pumpkinsplash.R
import com.example.pumpkinsplash.TransitionDayNight
import com.example.pumpkinsplash.presenter.common.constants.AnimationConstants.OFFSET_CLOUD2_X
import com.example.pumpkinsplash.presenter.common.constants.AnimationConstants.OFFSET_CLOUD2_Y
import com.example.pumpkinsplash.ui.theme.PumpkinSplashTheme

@Composable
fun Cloud2Animation(
    widthScreenPx: Int,
    transition: Transition<Boolean>,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    val offsetXInDp = with(density) { OFFSET_CLOUD2_X.toDp() }
    val offsetYInDp = with(density) { OFFSET_CLOUD2_Y }

    val translationCloud2 by transition.animateFloat(
        transitionSpec = { tween(1000, easing = EaseIn) },
        label = "translation cloud2"
    ) { isDay -> if (!isDay) /*-(93f+106.1f)*/ (-(widthScreenPx.toFloat())) else 0f }
    Box(
        Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.cloud_02),
            contentDescription = "moon",
            modifier = modifier
                .offset(offsetXInDp, offsetYInDp)
                .graphicsLayer(
                    translationX = translationCloud2,
                    clip = false,
                    alpha = 0.8f
                ),
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
fun Cloud2AnimationPreview() {
    PumpkinSplashTheme {
        TransitionDayNight()
    }
}