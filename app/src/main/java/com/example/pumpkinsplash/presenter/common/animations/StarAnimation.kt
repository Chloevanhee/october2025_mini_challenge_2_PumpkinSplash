package com.example.pumpkinsplash.presenter.common.animations

import androidx.compose.animation.core.EaseInOutCirc
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Alignment
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.pumpkinsplash.R
import com.example.pumpkinsplash.TransitionDayNight
import com.example.pumpkinsplash.ui.theme.PumpkinSplashTheme

@Composable
fun StarAnimation(transition: Transition<Boolean>, modifier: Modifier = Modifier) {
    val alphaStar by transition.animateFloat(
        transitionSpec = { tween(1000, easing = EaseInOutCirc) },
        label = "stars"
    ) { isDay -> if (!isDay) 1f else 0f }
    Box(
        modifier
            .fillMaxSize(),
           // .align(Alignment.TopCenter)
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(R.drawable.stars),
            contentDescription = "moon",
            modifier = Modifier.alpha(alphaStar)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun StarAnimationPreview() {
    PumpkinSplashTheme {
        TransitionDayNight()
    }
}