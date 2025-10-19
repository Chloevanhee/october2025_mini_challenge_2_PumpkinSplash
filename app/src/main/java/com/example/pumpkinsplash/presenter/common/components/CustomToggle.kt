package com.example.pumpkinsplash.presenter.common.components

import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateIntOffset
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.pumpkinsplash.R
import com.example.pumpkinsplash.presenter.common.animations.Cloud1Animation
import com.example.pumpkinsplash.presenter.common.animations.Cloud2Animation
import com.example.pumpkinsplash.presenter.common.animations.Cloud3Animation
import com.example.pumpkinsplash.presenter.common.animations.Graveyard
import com.example.pumpkinsplash.presenter.common.animations.SunAnimation
import com.example.pumpkinsplash.ui.theme.PumpkinSplashTheme

@Composable
fun CustomToggle(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    transition: Transition<Boolean>,
) {
    val density = LocalDensity.current

    val pumpkinWidth = 54.dp
    //val pumpkinHeight = 56.dp
    val horizontalPadding = 8.dp
    val customButtonWidth = 120.dp
    val endPositionOffsetToggle = with(density) {
        horizontalPadding.roundToPx()
    }
    val startPositionOffsetToggle = with(density) {
        (customButtonWidth.roundToPx() - pumpkinWidth.roundToPx() - 4 * horizontalPadding.roundToPx())
    }
    val offsetToggle by transition.animateIntOffset(
        transitionSpec = { tween(1000, easing = EaseIn) },
        label = "offset toggle animation"
    ) { isDay ->
        IntOffset(
            x = if (isDay) startPositionOffsetToggle else endPositionOffsetToggle,
            y = 0
        )
    }
    Box(
        modifier = modifier
            .padding(bottom = 20.dp, start = 4.dp, end = 4.dp, top = 4.dp)
            .size(120.dp, 60.dp)
            .background(Color.White, shape = RoundedCornerShape(size = 30.dp))
            .clickable {
                onCheckedChange(!isChecked)
            },
        contentAlignment = Alignment.CenterStart
    ) {
        Image(
            painterResource(R.drawable.pumpkin2),
            contentDescription = "pumpkin",
            modifier = Modifier
                .padding(horizontalPadding)
 //               .size(pumpkinWidth, pumpkinHeight)
                .scale(1.45f)
                .offset { offsetToggle }
                .offset(y = (-6).dp),
            alignment = Alignment.CenterStart,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ToggleAnimationPreview() {
    PumpkinSplashTheme {
        PumpkinSplashTheme {
            val isDayModeToggle = true
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
                CustomToggle(
                    isChecked = isDayModeToggle,
                    onCheckedChange = { /*todo*/ },
                    modifier = Modifier.align(Alignment.BottomCenter),
                    transition
                )
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