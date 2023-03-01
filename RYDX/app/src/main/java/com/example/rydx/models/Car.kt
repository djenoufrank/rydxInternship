package com.example.rydx.models

import com.example.rydx.R

data class Car(
    val id:String,
    val name: String,
    val price: Float,
    val description: String,
    val images: List<Int>,
)

fun getCars(): List<Car> {
return listOf(
    Car("1",
        "tesla 1",
        40.0f,
        "Decription Sample 1",
        images = listOf(R.drawable.image_1,R.drawable.image_1,R.drawable.image_1)
    ),
    Car("2",
        "tesla 2",
        45.5f,
        "Decription Sample 2",
        images = listOf(R.drawable.image_2,R.drawable.image_2, R.drawable.image_2)
    ),
    Car("3",
        "tesla 3",
        50.0f,
        "Decription Sample 3",
        images = listOf(R.drawable.image_3,R.drawable.image_3,R.drawable.image_3)
    ),
    Car("1",
        "tesla 1",
        40.0f,
        "Decription Sample 1",
        images = listOf(R.drawable.image_1,R.drawable.image_1,R.drawable.image_1)
    ),
    Car("2",
        "tesla 2",
        45.5f,
        "Decription Sample 2",
        images = listOf(R.drawable.image_2,R.drawable.image_2, R.drawable.image_2)
    ),
    Car("3",
        "tesla 3",
        50.0f,
        "Decription Sample 3",
        images = listOf(R.drawable.image_3,R.drawable.image_3,R.drawable.image_3)
    )

)
}