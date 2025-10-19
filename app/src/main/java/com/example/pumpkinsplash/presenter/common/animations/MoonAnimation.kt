package com.example.pumpkinsplash.presenter.common.animations

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
import com.example.pumpkinsplash.R
import com.example.pumpkinsplash.TransitionDayNight
import com.example.pumpkinsplash.presenter.common.constants.AnimationConstants.MOON_ANGLE
import com.example.pumpkinsplash.presenter.common.constants.AnimationConstants.SUN_MOON_PIVOT_FRACTION_X
import com.example.pumpkinsplash.presenter.common.constants.AnimationConstants.SUN_MOON_PIVOT_FRACTION_Y
import com.example.pumpkinsplash.presenter.common.constants.AnimationConstants.SUN_MOON_OFFSET_Y
import com.example.pumpkinsplash.ui.theme.PumpkinSplashTheme

@Composable
fun MoonAnimation(widthScreen: Dp, transition: Transition<Boolean>, modifier: Modifier = Modifier) {


    val moonRotation by transition.animateFloat(
        transitionSpec = { tween(1000, easing = EaseIn) },
        label = "moon animation"
    ) { isDay ->
        if (isDay) -MOON_ANGLE else 0f
    }

    Box(
        modifier
            .size(widthScreen)
            .graphicsLayer {
                this.transformOrigin =
                    TransformOrigin(SUN_MOON_PIVOT_FRACTION_X, SUN_MOON_PIVOT_FRACTION_Y)
                rotationZ = moonRotation
            },
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(R.drawable.moon),
            contentDescription = "moon",
            modifier = modifier
                .offset(y = SUN_MOON_OFFSET_Y)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MoonAnimationPreview() {
    PumpkinSplashTheme {
        TransitionDayNight()
    }
}