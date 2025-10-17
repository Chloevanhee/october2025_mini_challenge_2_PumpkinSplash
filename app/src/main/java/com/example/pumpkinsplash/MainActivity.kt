package com.example.pumpkinsplash

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.pumpkinsplash.presenter.viewmodel.SwitchHunterRoot
import com.example.pumpkinsplash.ui.theme.PumpkinSplashTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen().apply {
            setOnExitAnimationListener { screen ->
                val icon = screen.iconView

                // Centrer le pivot
                icon.pivotX = icon.width / 2f
                icon.pivotY = icon.height / 2f

                // 0.8s to 1.6s, shrinks to 0 and disappears
                val scaleX = ObjectAnimator.ofFloat(icon, View.SCALE_X, 0.6f, 0f)
                val scaleY = ObjectAnimator.ofFloat(icon, View.SCALE_Y, 0.6f, 0f)
                val rotate = ObjectAnimator.ofFloat(icon, View.ROTATION, 0f, 360f)

                val set = AnimatorSet().apply {
                    playTogether(scaleX, scaleY, rotate)
                    duration = 800
                    interpolator = AccelerateInterpolator()
                    doOnEnd { screen.remove() }
                }
                set.start()

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
                        modifier = Modifier.padding(/*innerPadding*/)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TransitionDayNight(modifier: Modifier = Modifier ) {
    SwitchHunterRoot(modifier)
}

@Preview(showBackground = true)
@Composable
fun TransitionDayNightPreview() {
    PumpkinSplashTheme {
        TransitionDayNight()
    }
}