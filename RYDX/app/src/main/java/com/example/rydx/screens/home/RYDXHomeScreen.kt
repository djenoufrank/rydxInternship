package com.example.rydx.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Preview
@Composable
fun RYDXHomeScreen(navController: NavController =
                         NavController(context = LocalContext.current)
) {
    Surface(modifier = Modifier.padding(1.dp)){
        Text(text ="home screen")
    }
}