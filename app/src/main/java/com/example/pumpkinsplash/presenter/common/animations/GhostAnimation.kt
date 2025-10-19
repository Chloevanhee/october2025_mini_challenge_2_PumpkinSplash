package com.example.pumpkinsplash.presenter.common.animations

import androidx.compose.animation.core.EaseInOutCirc
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.pumpkinsplash.R
import com.example.pumpkinsplash.ui.theme.PumpkinSplashTheme

@Composable
fun GhostAnimation(
    heightScreen: Dp,
    widthScreen: Dp,
    transition: Transition<Boolean>,
    modifier: Modifier = Modifier
) {
    val ghostOpacity by transition.animateFloat(
        transitionSpec = { tween(1300, easing = EaseInOutCirc) },
        label = "ghost opacity"
    ) { isDay -> if (isDay) 0f else 1f }

    val ghostScale by transition.animateFloat(
        transitionSpec = { tween(1300) },
        label = "ghost scale"
    ) { isDay -> if (isDay) 0.2f else 2f }

    val ghostTranslationX by transition.animateFloat(
        transitionSpec = { tween(1500, easing = EaseInOutCirc) },
        label = "ghost translation X"
    ) { isDay -> if (isDay) 100f else -100f }

    val ghostTranslationY by transition.animateFloat(
        transitionSpec = { tween(1500, easing = EaseInOutCirc) },
        label = "ghost translation Y"
    ) { isDay -> if (isDay) 100f else -150f }
    Box(
        modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.ghost),
            contentDescription = "ghost",
            modifier = modifier
                .offset(x = widthScreen * 0.35f, y = heightScreen * 1f) // at the end...
                .graphicsLayer(
                    scaleX = ghostScale,
                    scaleY = ghostScale,
                    translationX = ghostTranslationX,
                    translationY = ghostTranslationY,
                    clip = false,
                    alpha = ghostOpacity
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GhostAnimationPreview() {
    PumpkinSplashTheme {
        PumpkinSplashTheme {
            val isDayModeToggle = false
            val widthScreen = 411.dp
            val widthScreenPx = 1079
            val heightScreen = 731.dp
            val transition = updateTransition(isDayModeToggle, label = "animation")
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(clip = false),
                contentAlignment = BottomEnd
            ) {
                Graveyard(isDayModeToggle, transition)
                GhostAnimation(widthScreen, heightScreen, transition)
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
}