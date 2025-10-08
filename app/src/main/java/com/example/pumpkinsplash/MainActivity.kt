package com.example.pumpkinsplash

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.pumpkinsplash.ui.theme.PumpkinSplashTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen().apply {
            setOnExitAnimationListener { screen ->
                // fom 0 to 0.4 s, in 0.4s, slightly scales up and tilts to the left
                val zoomX = ObjectAnimator.ofFloat(
                    screen.iconView, View.SCALE_X, 0.5f, 0.7f
                )
                // zoomX.interpolator = OvershootInterpolator() // try to understand why I have a rebound effect
                zoomX.duration = 400

                val zoomY = ObjectAnimator.ofFloat(
                    screen.iconView, View.SCALE_Y, 0.5f, 0.7f
                )
                // zoomY.interpolator = OvershootInterpolator() // try to understand why I have a rebound effect
                zoomY.duration = 400

                val rotation = ObjectAnimator.ofFloat(screen.iconView, View.ROTATION, 0.0f, -15.0f)
                // rotation.interpolator = OvershootInterpolator() // try to understand why I have a rebound effect
                rotation.duration = 400

                // from 400 to 800 ms , tilts to the right
                val rotationR =
                    ObjectAnimator.ofFloat(screen.iconView, View.ROTATION, -15.0f, 15.0f)
                // rotationR.interpolator = OvershootInterpolator() // try to understand why I have a rebound effect
                rotationR.duration = 400
                rotationR.startDelay = 400

                // 0.8s to 1.6s, shrinks to 0 and disappears
                val zoomXF = ObjectAnimator.ofFloat(
                    screen.iconView, View.SCALE_X, 0.7f, 0f
                )
                //  zoomXF.interpolator = OvershootInterpolator() // try to understand why I have a rebound effect
                zoomXF.duration = 800
                zoomXF.startDelay = 800

                val zoomYF = ObjectAnimator.ofFloat(
                    screen.iconView, View.SCALE_Y, 0.8f, 0f
                )
                // zoomYF.interpolator = OvershootInterpolator() // try to understand why I have a rebound effect
                zoomYF.duration = 800
                zoomYF.startDelay = 800
                zoomYF.doOnEnd { screen.remove() }

                // PROBLEM: All animations are started at once, so they overlap instead of chaining
                // try to understand why I have a rebound effect
                zoomX.start()
                zoomY.start()
                rotation.start()
                rotationR.start()
                zoomXF.start()
                zoomYF.start()
            }
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PumpkinSplashTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android", modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PumpkinSplashTheme {
        Greeting("Android")
    }
}