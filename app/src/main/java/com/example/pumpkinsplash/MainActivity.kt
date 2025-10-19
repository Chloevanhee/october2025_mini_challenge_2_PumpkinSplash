package com.example.pumpkinsplash

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.pumpkinsplash.presenter.MainViewModel
import com.example.pumpkinsplash.ui.theme.PumpkinSplashTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {

    companion object {
        private const val TAG = "PumpkinSplash"
    }

    //private var keepOnScreen = true


    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel by viewModels<MainViewModel>()
        super.onCreate(savedInstanceState)

        Log.d(TAG, "🎃 MainActivity onCreate - Initializing splash screen")

        installSplashScreen().apply {
     /*       setKeepOnScreenCondition {
                val keepOnScreen = !viewModel.isReady
                keepOnScreen
            }*/
            setOnExitAnimationListener { screen ->

/*                // fom 0 to 0.4 s, in 0.4s, slightly scales up and tilts to the left
                val zoomX = ObjectAnimator.ofFloat(
                    screen.iconView, View.SCALE_X, 0.5f, 0.7f
                )
                zoomX.interpolator = LinearInterpolator() //LinearInterpolator
                zoomX.duration = 400L

                val zoomY = ObjectAnimator.ofFloat(
                    screen.iconView, View.SCALE_Y, 0.5f, 0.7f
                )
                zoomY.interpolator = LinearInterpolator()
                zoomY.duration = 400L*/

/*                val rotation = ObjectAnimator.ofFloat(screen.iconView, View.ROTATION, 0.0f, -15.0f)
                // rotation.interpolator = LinearInterpolator()
                rotation.duration = 400L*/

                // from 400 to 800 ms , tilts to the right
   /*             val rotationR =
                    ObjectAnimator.ofFloat(screen.iconView, View.ROTATION, -15.0f, 15.0f)
                // rotationR.interpolator = LinearInterpolator()
                rotationR.duration = 400L
                rotationR.startDelay = 400L*/
              //  rotation.doOnEnd { screen.remove() }
/*                // 0.8s to 1.6s, shrinks to 0 and disappears
                val zoomXF = ObjectAnimator.ofFloat(
                    screen.iconView, View.SCALE_X, 0.6f, 0f
                )
                zoomXF.duration = 800L
                zoomXF.startDelay = 800L
                val zoomYF = ObjectAnimator.ofFloat(
                    screen.iconView, View.SCALE_Y, 0.6f, 0f
                )*/
                // zoomYF.interpolator = OvershootInterpolator()
                /*                zoomX.start()
                                zoomY.start()*/
                //rotation.start()
                /*                rotationR.start()*/
                /*             zoomYF.duration = 800L
                             zoomYF.startDelay = 800L
                             zoomYF.doOnEnd { screen.remove() }
                             zoomXF.start()
                             zoomYF.start()*/

                val rotation = ObjectAnimator.ofFloat(screen.iconView, View.ROTATION, 0f, -15f).apply {
                    duration = 800
                    interpolator = AccelerateDecelerateInterpolator()
                    doOnEnd {
                        // Optionnel : revenir à la rotation d'origine
                        ObjectAnimator.ofFloat(screen.iconView, View.ROTATION, -15f, 0f).apply {
                            duration = 400
                            start()
                        }
                    }
                }
                rotation.start()

            }
        }

        Log.d(TAG, "🎃 Splash screen setup complete")
        /*        lifecycleScope.launch {
                    // 1000ms is the maximum recommended animation length
                    delay(1250) // a little over for this test
                    keepOnScreen = false
                }*/
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

        Log.d(TAG, "🎃 MainActivity onCreate complete")
    }

    private fun formatTime(timeMillis: Long): String {
        val dateFormat = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault())
        return dateFormat.format(Date(timeMillis))
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
