package com.example.pumpkinsplash

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseInOutCirc
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateIntOffset
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.pumpkinsplash.ui.theme.HunterSwitcherday
import com.example.pumpkinsplash.ui.theme.HunterSwitchernight
import com.example.pumpkinsplash.ui.theme.PumpkinSplashTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen().apply {
            setOnExitAnimationListener { screen ->
                // 0.8s to 1.6s, shrinks to 0 and disappears
                val zoomXF = ObjectAnimator.ofFloat(
                    screen.iconView, View.SCALE_X, 0.6f, 0f
                )
                zoomXF.duration = 800

                val zoomYF = ObjectAnimator.ofFloat(
                    screen.iconView, View.SCALE_Y, 0.6f, 0f
                )
                zoomYF.duration = 800
                zoomYF.doOnEnd { screen.remove() }

                zoomXF.start()
                zoomYF.start()
            }
        }
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge() //or I comment this...

        setContent {
            PumpkinSplashTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    TransitionDayNight(
                        modifier = Modifier.padding()
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TransitionDayNight(modifier: Modifier = Modifier) {
    val widthScreen = LocalConfiguration.current.screenWidthDp.dp
    var isDayModeToggle by remember { mutableStateOf(true) }
    val transition = updateTransition(isDayModeToggle, label = "animation")
    val pumpkinSize = 40.dp // how to know the size of the image...
    val horizontalPadding = 10.dp
    val customButtonWidth = 120.dp
    val density = LocalDensity.current
    val endPositionOffsetToggle = with(density) {
        horizontalPadding.roundToPx()
    }
    val startPositionOffsetToggle = with(density) {
        (customButtonWidth.roundToPx() - pumpkinSize.roundToPx() - 2 * horizontalPadding.roundToPx())

    }
    val widthScreenPx = with(density) {
        widthScreen.roundToPx()
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
    val sunRotation by transition.animateFloat(
        transitionSpec = { tween(1000, easing = EaseIn) },
        label = "overly animation"
    ) { isDay ->
        if (isDay) 0f else -33f
    }
    val moonRotation by transition.animateFloat(
        transitionSpec = { tween(1000, easing = EaseIn) },
        label = "overly animation"
    ) { isDay ->
        if (isDay) 33f else 0f
    }
    val translationCloud1 by transition.animateFloat(
        transitionSpec = { tween(1000, easing = EaseIn) },
        label = "translation cloud1"
    ) { isDay -> if (!isDay) /*-262.49f*/ (-(widthScreenPx.toFloat())) else 0f }
    val translationCloud2 by transition.animateFloat(
        transitionSpec = { tween(1000, easing = EaseIn) },
        label = "translation cloud2"
    ) { isDay -> if (!isDay) /*-(93f+106.1f)*/ (-(widthScreenPx.toFloat())) else 0f }
    val translationCloud3 by transition.animateFloat(
        transitionSpec = { tween(1000, easing = EaseIn) },
        label = "translation cloud3"
    ) { isDay -> if (!isDay) (widthScreenPx.toFloat()) /*249.8f */ else 0f }

    val alphaStar by transition.animateFloat(
        transitionSpec = { tween(1000, easing = EaseInOutCirc) },
        label = "stars"
    ) { isDay -> if (!isDay) 1f else 0f }
    val ghostOpacity by transition.animateFloat(
        transitionSpec = { tween(1300, easing = EaseInOutCirc) },
        label = "ghost opacity"
    ) { isDay -> if (isDay) 0f else 1f }

    val ghostScale by transition.animateFloat(
        transitionSpec = { tween(1300) },
        label = "ghost scale"
    ) { isDay -> if (isDay) 0.2f else 1f }

    val ghostTranslationX by transition.animateFloat(
        transitionSpec = { tween(1500, easing = EaseInOutCirc) },
        label = "ghost translation X"
    ) { isDay -> if (isDay) -300f else 0f }

    val ghostTranslationY by transition.animateFloat(
        transitionSpec = { tween(1500, easing = EaseInOutCirc) },
        label = "ghost translation Y"
    ) { isDay -> if (isDay) 100f else 0f }

    Box(
        modifier = modifier
            .fillMaxSize()
            .graphicsLayer(clip = false),  // Disable clipping at root level
         contentAlignment = BottomEnd
    ) {
        Graveyard(isDayModeToggle, backgroundColorAnimated, overlay, modifier)
        //sun
        Box(
            modifier
                .size(widthScreen)
                .align(Alignment.TopCenter)
                .graphicsLayer {
                    this.transformOrigin = TransformOrigin(0.5f, 2f)
                    rotationZ = sunRotation
                },

            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.sun),
                contentDescription = "Sun",
                modifier = Modifier
                    .graphicsLayer {
                        rotationZ = sunRotation
                    }
            )
        }
        //moon
        Box(
            modifier
                .size(widthScreen)
                .align(Alignment.TopCenter)
                .graphicsLayer {
                    this.transformOrigin = TransformOrigin(0.5f, 2f)
                    rotationZ = moonRotation
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.moon),
                contentDescription = "moon",
                modifier = Modifier
                    .graphicsLayer {
                        rotationZ = moonRotation
                    }
            )
        }
        //cloud 2
        Box(
            Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)
                .graphicsLayer(translationX = translationCloud1)
        ) {
            Image(
                painter = painterResource(R.drawable.cloud_02),
                contentDescription = "moon",
                modifier = Modifier
                    .offset(93.dp, 99.dp)
                    .alpha(0.8f)
                    .graphicsLayer(translationX = translationCloud1)
            )
        }
        //cloud 1
        Box(
            Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)
        ) {
            Image(
                painter = painterResource(R.drawable.cloud_01),
                contentDescription = "moon",
                modifier = Modifier
                    .offset((-56).dp, 244.dp)
                    .alpha(0.5f)
                    .graphicsLayer(translationX = translationCloud2)
            )
        }
        //cloud 3
        Box(
            Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)
        ) {
            Image(
                painter = painterResource(R.drawable.cloud_03),
                contentDescription = "moon",
                modifier = Modifier
                    .offset(x = 270.dp, y = 166.dp)
                    .graphicsLayer(translationX = translationCloud3)
            )
        }
        //Star
        Box(
            Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)
        ) {
            Image(
                painter = painterResource(R.drawable.stars),
                contentDescription = "moon",
                modifier = Modifier.alpha(alphaStar)


            )
        }
        //Ghost
        Box(
            Modifier
                .fillMaxSize()
                .graphicsLayer(clip = false)
        ) {
            Image(
                painter = painterResource(R.drawable.ghost),
                contentDescription = "ghost",
                modifier = Modifier
                    .offset(x = 219.dp, y = 317.dp) // at the end...
                    .alpha(ghostOpacity)
                    .graphicsLayer(
                        scaleX = ghostScale,
                        scaleY = ghostScale,
                        translationX = ghostTranslationX,
                        translationY = ghostTranslationY,
                        clip = false
                    )
            )
        }
        //custom button
        CustomToggle(
            isChecked = isDayModeToggle,
            onCheckedChange = { isDayModeToggle = it },
            modifier = Modifier.align(Alignment.BottomCenter),
            pumpkinSize = pumpkinSize,
            horizontalPadding = horizontalPadding,
            offsetToggle = offsetToggle
        )
    }
}

@Composable
fun CustomToggle(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    pumpkinSize: Dp,
    horizontalPadding: Dp,
    offsetToggle: IntOffset
) {
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
                .size(pumpkinSize)
                .offset { offsetToggle },
            alignment = Alignment.CenterStart,
        )
    }
}

@Composable
fun Graveyard(
    isDayMode: Boolean,
    backgroundColorAnimated: Color,
    overlay: Float,
    modifier: Modifier = Modifier
) {
    val scroll = rememberScrollState(
        initial = if (isDayMode) Int.MAX_VALUE else 0
    )
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
            modifier = Modifier
        )
        //dark overley
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = overlay))
        )
    }

}

@Preview(showBackground = true)
@Composable
fun TransitionDayNightPreview() {
    PumpkinSplashTheme {
        TransitionDayNight()
    }
}