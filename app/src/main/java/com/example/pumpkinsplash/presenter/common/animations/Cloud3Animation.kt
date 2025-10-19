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
import androidx.compose.ui.tooling.preview.Preview
import com.example.pumpkinsplash.R
import com.example.pumpkinsplash.TransitionDayNight
import com.example.pumpkinsplash.presenter.common.constants.AnimationConstants.OFFSET_CLOUD3_X
import com.example.pumpkinsplash.presenter.common.constants.AnimationConstants.OFFSET_CLOUD3_Y
import com.example.pumpkinsplash.ui.theme.PumpkinSplashTheme

@Composable
fun Cloud3Animation(
    widthScreenPx: Int,
    transition: Transition<Boolean>,
    modifier: Modifier = Modifier
) {

    val density = LocalDensity.current
    val offsetXInDp = with(density) { OFFSET_CLOUD3_X }
    val offsetYInDp = with(density) { OFFSET_CLOUD3_Y }
    val translationCloud3 by transition.animateFloat(
        transitionSpec = { tween(1000, easing = EaseIn) },
        label = "translation cloud3"
    ) { isDay -> if (!isDay) (widthScreenPx.toFloat()) /*249.8f */ else 0f }
    Box(
        modifier
            .fillMaxSize(),
    ) {
        Image(
            painter = painterResource(R.drawable.cloud_03),
            contentDescription = "moon",
            modifier = Modifier
                .offset(x = offsetXInDp, y = offsetYInDp)
                .graphicsLayer(translationX = translationCloud3)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Cloud3AnimationPreview() {
    PumpkinSplashTheme {
        TransitionDayNight()
    }
}