package com.example.pumpkinsplash

import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.pumpkinsplash.ui.theme.PumpkinSplashTheme

@Composable
fun SunAnimation(widthScreen: Dp, transition: Transition<Boolean>, modifier: Modifier = Modifier) {
    val sunRotation by transition.animateFloat(
        transitionSpec = { tween(1000, easing = EaseIn) },
        label = "overly animation"
    ) { isDay ->
        if (isDay) 0f else -50f
    }

    Box(
        modifier
            .size(widthScreen)
            .graphicsLayer {
                this.transformOrigin = TransformOrigin(0.5f, 2f)
                rotationZ = sunRotation
            },
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(R.drawable.sun),
            contentDescription = "Sun",
            modifier = modifier
                .graphicsLayer {
                    rotationZ = sunRotation
                }
                .offset(y=172.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SunAnimationPreview() {
    PumpkinSplashTheme {
        TransitionDayNight()
    }
}