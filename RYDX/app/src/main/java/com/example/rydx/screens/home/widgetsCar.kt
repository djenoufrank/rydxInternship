package com.example.rydx.screens.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.rydx.models.Car
import com.example.rydx.models.getCars

@ExperimentalAnimationApi
@Preview
@Composable
fun CarRow(
    car: Car = getCars()[0],
    onItemClick: (String) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .padding(0.dp, 6.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick(car.id)
            },
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 5.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {


                Image(
                    modifier = Modifier
                        .heightIn(0.dp, 400.dp)
                        .fillMaxWidth(),
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current).data(data = car.images[0])
                            .apply(block = fun ImageRequest.Builder.() {
                                crossfade(true)
                            }).build()
                    ),
                    contentDescription = "Car"
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = car.name,
                    style = MaterialTheme.typography.h4
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Price : ${car.price}€/Day",
                    style = MaterialTheme.typography.h5
                )
            }
        }
    }

}
