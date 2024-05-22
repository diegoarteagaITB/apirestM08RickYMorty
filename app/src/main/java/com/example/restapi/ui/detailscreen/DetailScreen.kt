package com.example.restapi.ui.detailscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.restapi.mvvm.CharactersViewModel
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.example.restapi.model.Character
import com.example.restapi.ui.listscreen.RemoteImage
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.restapi.navigation.AppScreens


@Composable
fun DetailScreen(navController: NavController, viewModel: CharactersViewModel, id: Int?) {
    var presses by remember { mutableStateOf(0) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(route = AppScreens.ListScreen.route)
            }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(Color.DarkGray),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Content(navController, id, viewModel)
        }
    }
}

@Composable
fun Content(navController: NavController, id: Int?, viewModel: CharactersViewModel) {

    val character by viewModel.character.observeAsState()

    LaunchedEffect(viewModel) {
        viewModel.getCharacterById(id.toString())
    }
    val screenHeight = with(LocalDensity.current) { LocalConfiguration.current.screenHeightDp.dp }



    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        character?.let { CharacterCard(it, screenHeight) }

    }
}

@Composable
fun CharacterCard(character: Character, screenHeight: Dp) {
    val cardHeight = screenHeight * 0.8f // 50% of the screen height

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight)
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black
        )

    ) {
        Spacer(modifier = Modifier
            .height(4.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .background(Color.LightGray, RoundedCornerShape(16.dp))
        ) {
            MyText(
                text = character.name,
                size = 30,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                letterSpacing = 7
            )
        }
        Spacer(modifier = Modifier
            .height(4.dp))

        RemoteImage(url = character.image, cornerRadius = 20.dp)
        Row(
            modifier = Modifier
                .padding(start = 10.dp)
        ) {
            MyText(
                text = "Status: ",
                size = 24,
                color = Color.LightGray,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1
            )
            MyText(
                text = character.status,
                size = 24,
                color = Color.LightGray,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1
            )
        }
        Row(
            modifier = Modifier
                .padding(start = 10.dp)
        ) {
            MyText(
                text = "Genere: ",
                size = 20,
                color = Color.LightGray,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1
            )
            MyText(
                text = character.gender,
                size = 20,
                color = Color.LightGray,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1
            )
        }
        Row(
            modifier = Modifier
                .padding(start = 10.dp)
        ) {
            MyText(
                text = "Origin: ",
                size = 24,
                color = Color.LightGray,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1
            )
            MyText(
                text = character.origin.name,
                size = 24,
                color = Color.LightGray,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1
            )
        }


    }
}
/*
@Composable
@ComposableTarget
public fun Text(
    text: String,
    modifier: Modifier = COMPILED_CODE,
    color: Color = COMPILED_CODE,
    fontSize: TextUnit = COMPILED_CODE,
    fontStyle: FontStyle? = COMPILED_CODE,
    fontWeight: FontWeight? = COMPILED_CODE,
    fontFamily: FontFamily? = COMPILED_CODE,
    letterSpacing: TextUnit = COMPILED_CODE,
    textDecoration: TextDecoration? = COMPILED_CODE,
    textAlign: TextAlign? = COMPILED_CODE,
    lineHeight: TextUnit = COMPILED_CODE,
    overflow: TextOverflow = COMPILED_CODE,
    softWrap: Boolean = COMPILED_CODE,
    maxLines: Int = COMPILED_CODE,
    minLines: Int = COMPILED_CODE,
    onTextLayout: (TextLayoutResult) -> Unit = COMPILED_CODE,
    style: TextStyle = COMPILED_CODE
): Unit
 */



@Composable
fun MyText(text: String, size: Int, color: Color, fontWeight: FontWeight, letterSpacing: Int) {

    Text(
        text = text,
        modifier = Modifier
            .padding(4.dp),
        textAlign = TextAlign.Center,
        style = TextStyle(
            fontSize = size.sp,
            color = color,
            fontWeight = fontWeight,
            letterSpacing = letterSpacing.sp

        )
    )
}