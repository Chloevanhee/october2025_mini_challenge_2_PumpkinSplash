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