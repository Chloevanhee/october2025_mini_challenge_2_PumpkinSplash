package com.example.pumpkinsplash

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pumpkinsplash.ui.theme.PumpkinSplashTheme

@Composable
fun Cloud1Animation(
    widthScreenPx: Int,
    transition: Transition<Boolean>,
    modifier: Modifier = Modifier
) {

    val translationCloud1 by transition.animateFloat(
        transitionSpec = { tween(1000, easing = EaseIn) },
        label = "translation cloud1"
    ) { isDay -> if (!isDay) /*-262.49f*/ (-(widthScreenPx.toFloat())) else 0f }
    Box(
        Modifier
            .fillMaxSize(),
            //.align(Alignment.TopCenter)
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(R.drawable.cloud_01),
            contentDescription = "moon",
            modifier = modifier
                .offset((-56).dp, 244.dp)
                .alpha(0.5f)
                .graphicsLayer(translationX = translationCloud1)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun Cloud1AnimationPreview() {
    PumpkinSplashTheme {
        TransitionDayNight()
    }
}