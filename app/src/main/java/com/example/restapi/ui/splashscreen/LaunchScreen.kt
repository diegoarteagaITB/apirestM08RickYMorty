package com.example.restapi.ui.splashscreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.restapi.R
import com.example.restapi.navigation.AppScreens
import kotlinx.coroutines.delay


@Composable
fun LaunchScreen(navController: NavController) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 3000)
    )
    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(1500)
        navController.popBackStack()
        navController.navigate(route = AppScreens.ListScreen.route)
    }
    SplashContent(alphaAnim.value)
}
@Composable
fun SplashContent(alphaAnim: Float) {

    val colorPumpking = "0xFFFAF8F3"
    val colorRed = "0xFFD11010"

    Column(
        modifier = Modifier
            .fillMaxSize() // Ocupa todo el tamaño de la pantalla
            .padding(16.dp), // Ajusta el espaciado según sea necesario
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth() // Ocupa todo el ancho disponible en el Column
                .fillMaxHeight() // Altura fija en este caso
                .background(Color(0xFFFAF8F3)) ,
            //.background(Color(android.graphics.Color.parseColor("#FAF8F3"))),
            //.background(Color(android.graphics.Color.parseColor("#FAF8F3")))
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.splash_logo),
                contentDescription = "logo",
                modifier = Modifier.
                fillMaxWidth() // Ocupa todo el ancho disponible
                    .aspectRatio(1f) // Relación de aspecto cuadrada
                    .scale(1f)


            )
        }
    }
}

