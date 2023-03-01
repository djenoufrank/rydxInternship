package com.example.rydx.screens.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.rydx.models.getCars
import com.google.accompanist.pager.*
import kotlin.math.absoluteValue
@ExperimentalAnimationApi
@Composable
fun DetailsScreenCar(navController: NavController,
                     myCar: String?) {
    val newCarList = getCars().filter { car ->
        car.id == myCar
    }
    Surface(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth(), color = Color.White) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top) {

            LazyRow {
                items(newCarList[0].images) { image ->
                    Card(
                        modifier = Modifier
                            .padding(16.dp)
                            .size(500.dp,400.dp),
                        shape = RoundedCornerShape(10.dp),
                        elevation = 8.dp
                    ) {
                        Column {
                            Image(   modifier = Modifier
                                .fillMaxSize(),
                                contentScale = ContentScale.Crop,painter = rememberAsyncImagePainter(model = image),contentDescription = "Car")
                        }
                    }
                }
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,modifier=Modifier.fillMaxWidth().padding(5.dp)) {
                Text(
                    text = newCarList[0].name,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(5.dp), color = Color.Black
                )
                Text(
                    text = newCarList[0].description,
                    modifier = Modifier.padding(5.dp), color = Color.Black
                )
            }
            Column(verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .padding(5.dp)
                    .background(color = Color.Black)
                    .fillMaxWidth()
            ) {
                Row( horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Price \n${newCarList[0].price}€/Day", modifier = Modifier
                        .padding(5.dp).align(Alignment.CenterVertically), color = Color.White)
                    Button(onClick = { }, modifier = Modifier
                        .padding(5.dp)
                        .width(120.dp)
                        .height(60.dp),
                        elevation = ButtonDefaults.elevation(
                            defaultElevation = 10.dp,
                            pressedElevation = 15.dp,
                            disabledElevation = 0.dp
                        ),colors=ButtonDefaults.buttonColors(backgroundColor = Color.White)) {
                        Text(text = "Book Now", color = Color.Black)
                    }
                }



            }
        }
    }
}