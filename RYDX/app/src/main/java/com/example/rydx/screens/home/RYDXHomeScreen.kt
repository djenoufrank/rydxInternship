package com.example.rydx.screens.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rydx.models.Car
import com.example.rydx.models.getCars
import com.example.rydx.navigation.RYDXScreens

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun RYDXHomeScreen(navController: NavController =
                       NavController(context = LocalContext.current)
) {
   val carList: List<Car> = getCars()
    Column(modifier = Modifier.padding(15.dp)) {
        LazyColumn {
            items(items = carList) {
                CarRow(car = it){car ->
                    navController.navigate(route = RYDXScreens.DetailsScreenCar.name+"/$car")

                }
            }
        }

    }

}