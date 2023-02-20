package com.example.rydx.screens

import android.text.style.BackgroundColorSpan
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.rydx.navigation.RYDXScreens
import kotlinx.coroutines.delay
import com.example.rydx.R
import androidx.compose.ui.res.painterResource

@Preview
@Composable
fun RYDXSplashScreen(navController: NavController = NavController(context = LocalContext.current)){
    val image = painterResource(R.drawable.img)

    val scale= remember{
        Animatable(0f)
    }
    LaunchedEffect(key1 = true){
        scale.animateTo(targetValue = 0.9f,
            animationSpec = tween(durationMillis = 800,
                easing = {
                    OvershootInterpolator(8f).getInterpolation(it)
                })
        )
        delay(2000L)
            navController.navigate(RYDXScreens.OpeningScreen.name)
    }
        Image(
            painter = image,
            contentDescription = "splash image",
            modifier = Modifier.fillMaxSize()

        )
}
