package com.example.restapi.ui.listscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.restapi.model.Character
import com.example.restapi.mvvm.CharactersViewModel
import com.example.restapi.navigation.AppScreens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navController: NavController, viewModel: CharactersViewModel) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Rick y Morty: Lista de personajes")
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    MyIconButtonWithTextBelow(
                        text = "Todos",
                        icon = Icons.Rounded.AccountCircle,
                        contentDescription = "Boton para mostrar todos los personajes",
                        onClick = {
                            println("Todos - !!!!!!!!!!!!!!!!!!!")
                        }
                    )
                    MyIconButtonWithTextBelow(
                        text ="Fav" ,
                        icon = Icons.Rounded.Favorite,
                        contentDescription = "Boton para mostrar los personajes favoritos",
                        onClick = {
                            println("Favortos - !!!!!!!!!!!!!!!!!!!!")
                        })
                }

            }
        },

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),

        ) {
            ListScreenContent(navController,viewModel)
        }
    }
}

@Composable
fun ListScreenContent(navController: NavController,viewModel: CharactersViewModel) {

    val charactersList by viewModel.charactersList.observeAsState(initial = emptyList())


    LaunchedEffect(viewModel) {
        viewModel.getCharacters()
    }
    ListView(characters = charactersList, navController)


}

@Composable
fun MyIconButtonWithTextBelow(
    text: String,
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit
) {
   Box(){
       Column(modifier = Modifier,
           horizontalAlignment = Alignment.CenterHorizontally,
           ) {
           Icon(
               icon,
               contentDescription = contentDescription,
               Modifier.clickable(onClick = onClick)
           )
           Text(text = text)
       }
   }

}

/*
Icon(
            Icons.Rounded.ShoppingCart,
            contentDescription = stringResource(id = R.string.shopping_cart_content_desc)
        )
 */

@Composable
fun ListView(characters: List<Character>,navController: NavController){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        LazyColumn(

        ){
            items(characters){
                    characters ->
                ItemElement(character = characters, onClick = { navController.navigate("${AppScreens.DetailScreen.route}/${characters.id}") })
            }
        }
    }
    
}

@Composable
fun ItemElement(character: Character,
                onClick: (Int) -> Unit
                ){

    Box(
        modifier = Modifier
            .padding(5.dp)
            .height(100.dp)
            .clickable { onClick(character.id) },
        //.height(50.dp)
    ) {
        Row {
            RemoteImage(url = character.image,
                cornerRadius = 16.dp,
                modifier = Modifier.size(100.dp))
            Box(modifier = Modifier
                .width(10.dp)) {
            }
            Column(
                Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(text = character.name,
                    fontSize = 30.sp,
                    color = Color.White
                )
                Row {
                    Text(text = "${character.species} - ",
                        color = Color.LightGray)
                    Text(text = " ${character.status}",
                        color = if (character.status == "Alive"){
                            Color.Green
                        }else if (character.status == "Dead"){
                            Color.Red
                        }else{
                            Color.Blue
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun RemoteImage(
    url: String,
    cornerRadius: Dp,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    // Cargar la imagen desde la URL
    val painter = rememberImagePainter(
        data = url,
        builder = {
            // Configurar opciones de la imagen si es necesario
        }
    )

    // Componer la imagen con esquinas redondeadas
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius)) // Clip con esquinas redondeadas
            .aspectRatio(1f), // Relación de aspecto cuadrada
        contentScale = ContentScale.Crop // Escala la imagen para que llene el espacio
    )
}