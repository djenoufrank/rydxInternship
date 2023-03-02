package com.example.rydx.screens.home

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.rydx.models.getCars
import com.google.accompanist.pager.*
import kotlin.math.absoluteValue

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class)
@ExperimentalAnimationApi
@Composable
fun DetailsScreenCar(
    navController: NavController,
    myCar: String?
) {
    val newCarList = getCars().filter { car ->
        car.id == myCar
    }
    val pagerState = rememberPagerState(
        pageCount = newCarList[0].images.size
    )
    val scrollState = rememberScrollState()
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(scrollState)
            .fillMaxWidth(), color = Color.White
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Text(
                text = newCarList[0].name,
                color = Color.Black,
                fontSize = 45.sp,
                modifier = Modifier.padding(10.dp)
            )
        }
        Column(modifier = Modifier
            .padding(0.dp, 50.dp, 0.dp, 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .weight(1f)
                    .padding(0.dp, 50.dp, 0.dp, 20.dp),
                verticalAlignment = Alignment.Top
            ) { image ->

                Card(
                    modifier = Modifier
                        .graphicsLayer {
                            val pageOffset = calculateCurrentOffsetForPage(image).absoluteValue

                            lerp(
                                start = 0.85f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            ).also { scale ->
                                scaleX = scale
                                scaleY = scale

                            }
                            alpha = lerp(
                                start = 0.5f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )

                        }
                        .padding(16.dp)
                        .size(300.dp, 250.dp),
                    shape = RoundedCornerShape(10.dp),
                    elevation = 8.dp
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center)
                    ) {
                        Image(
                            painter = painterResource(
                                id = newCarList[0].images[image]
                            ),
                            contentDescription = "Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )


                    }
                }

            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {

                Text(
                    text = newCarList[0].description,
                    modifier = Modifier.padding(5.dp), color = Color.Black
                )
            }
            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .padding(5.dp)
                    .background(color = Color.Black)
                    .fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Price \n${newCarList[0].price}€/Day", modifier = Modifier
                            .padding(5.dp)
                            .align(Alignment.CenterVertically), color = Color.White
                    )
                    Button(
                        onClick = { }, modifier = Modifier
                            .padding(5.dp)
                            .width(120.dp)
                            .height(60.dp),
                        elevation = ButtonDefaults.elevation(
                            defaultElevation = 10.dp,
                            pressedElevation = 15.dp,
                            disabledElevation = 0.dp
                        ), colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                    ) {
                        Text(text = "Book Now", color = Color.Black)
                    }
                }


            }
        }
    }
}