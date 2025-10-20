package com.example.pumpkinsplash.presenter.common.constants

import androidx.compose.ui.unit.dp

/* ScreenSize in Px: 1079 x 2399
   ScreenSize: 411.0.dp x 914.0.dp
*/
object AnimationConstants {
    const val MOON_ANGLE = 60f
    const val SUN_ANGLE = 60f
    const val SUN_MOON_PIVOT_FRACTION_X = 0.5f
    const val SUN_MOON_PIVOT_FRACTION_Y = 1.5f
    val SUN_MOON_OFFSET_Y = 172.dp


    const val OFFSET_CLOUD1_X = -56f - 131.3f //(262.49/2)
    const val OFFSET_CLOUD1_Y = 600f//244f + 82.26f//(82.26f /2f)

    const val OFFSET_CLOUD2_X = 93f + 106.14f
    val OFFSET_CLOUD2_Y = 99.dp//99f + 59.06f
    val OFFSET_CLOUD3_X = 270.dp//270 + 249.8f
    val OFFSET_CLOUD3_Y = 166.dp//166f + 94.48f
}
