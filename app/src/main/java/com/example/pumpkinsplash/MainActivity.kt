package com.example.pumpkinsplash

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.pumpkinsplash.ui.theme.PumpkinSplashTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // super.onCreate(savedInstanceState)

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
            //setKeepOnScreenCondition
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
    val heightScreen = LocalConfiguration.current.screenHeightDp.dp

    var isDayModeToggle by remember { mutableStateOf(true) }
    val transition = updateTransition(isDayModeToggle, label = "animation")
    val pumpkinWidth = 54.dp
    val pumpkinHeight = 56.dp
    val horizontalPadding = 10.dp
    val customButtonWidth = 120.dp
    val density = LocalDensity.current
    val endPositionOffsetToggle = with(density) {
        horizontalPadding.roundToPx()
    }
    val startPositionOffsetToggle = with(density) {
        (customButtonWidth.roundToPx() - pumpkinWidth.roundToPx() - 2 * horizontalPadding.roundToPx())
    }
    val widthScreenPx = with(density) {
        widthScreen.roundToPx()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .graphicsLayer(clip = false),  // Disable clipping at root level
        contentAlignment = BottomEnd
    ) {
        Graveyard(isDayModeToggle, transition)
        GhostAnimation(widthScreen, heightScreen, transition)
        CustomToggle(
            isChecked = isDayModeToggle,
            onCheckedChange = { isDayModeToggle = it },
            modifier = Modifier.align(Alignment.BottomCenter),
            pumpkinWidth = pumpkinWidth,
            pumpkinHeight = pumpkinHeight,
            horizontalPadding = horizontalPadding,
            startPositionOffsetToggle, endPositionOffsetToggle,
            transition
        )
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .graphicsLayer(clip = false),//,  // Disable clipping at root level
        //.background(color = Color.Red),
        contentAlignment = TopCenter
    ) {
        SunAnimation(widthScreen, transition)
        MoonAnimation(widthScreen, transition)
        Cloud2Animation(widthScreenPx, transition)
        Cloud1Animation(widthScreenPx, transition)
        Cloud3Animation(widthScreenPx, transition)
        StarAnimation(transition)
    }
}

@Preview(showBackground = true)
@Composable
fun TransitionDayNightPreview() {
    PumpkinSplashTheme {
        TransitionDayNight()
    }
}