package com.example.pumpkinsplash.presenter.common.animations

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.pumpkinsplash.R
import com.example.pumpkinsplash.TransitionDayNight
import com.example.pumpkinsplash.ui.theme.HunterSwitcherday
import com.example.pumpkinsplash.ui.theme.HunterSwitchernight
import com.example.pumpkinsplash.ui.theme.PumpkinSplashTheme

@Composable
fun Graveyard(
    isDayMode: Boolean,

    transition:Transition<Boolean>,
    modifier: Modifier = Modifier
) {
    val scroll = rememberScrollState(
        initial = if (isDayMode) Int.MAX_VALUE else 0
    )

    val backgroundColorAnimated by transition.animateColor(
        { tween(1000, easing = EaseIn) },
        label = "Background color animated"
    ) { isDay -> if (isDay) HunterSwitcherday else HunterSwitchernight }
    val overlay by transition.animateFloat(
        transitionSpec = { tween(1000, easing = EaseIn) },
        label = "overly animation"
    ) { isDay ->
        if (isDay) 0f else 0.5f
    }
    LaunchedEffect(key1 = isDayMode) {
        val target = if (isDayMode) scroll.maxValue else 0
        scroll.animateScrollTo( //should we go to use updateTransition?
            value = target,
            animationSpec = tween(durationMillis = 1000, easing = EaseIn)
        )
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .horizontalScroll(scroll, true)
            .background(backgroundColorAnimated)
            .graphicsLayer(clip = false),  // Disable clipping in Graveyard

        contentAlignment = Alignment.BottomCenter
    ) {
        //graveyard
        Image(
            painter = painterResource(R.drawable.graveyard),
            contentDescription = "Graveyard background scrollable",
            alignment = if (isDayMode) Alignment.CenterStart else Alignment.CenterEnd,
            modifier = modifier
        )
        //dark overley
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = overlay))
        )
    }

}
@Preview(showBackground = true)
@Composable
fun GraveyardAnimationPreview() {
    PumpkinSplashTheme {
        TransitionDayNight()
    }
}