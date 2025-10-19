package com.example.pumpkinsplash

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.pumpkinsplash.presenter.viewmodel.SwitchHunterRoot
import com.example.pumpkinsplash.ui.theme.PumpkinSplashTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        var showSplash = true
        lifecycleScope.launch {
            delay(5000L)
            showSplash = false
        }

        installSplashScreen().setKeepOnScreenCondition {
            showSplash
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

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