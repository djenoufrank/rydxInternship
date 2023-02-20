package com.example.rydx.screens.opening

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rydx.R
import com.example.rydx.navigation.RYDXScreens

@Preview
@Composable
fun RYDXOpeningScreen(navController: NavController = NavController(context = LocalContext.current)) {

    Surface(modifier = Modifier.fillMaxSize(),color=Color.White) {
        Column(
            modifier = Modifier.fillMaxWidth().heightIn(0.dp,400.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            val image = painterResource(R.drawable.img)
            Image(painter = image,contentDescription = "image logo",
                modifier = Modifier.fillMaxWidth().heightIn(0.dp,400.dp))
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.height(100.dp))
            Logo()
            Spacer(modifier = Modifier.height(20.dp))
            Text(text="Now your finances are in one place and \n" +
                    "                always under control", color = Color.Black)

        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Bottom(navController)
        }
    }

}

@Composable
private fun Bottom(navController: NavController) {
    SubmitButton("Sign In",
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black), onClick = {
            navController.navigate(RYDXScreens.LoginScreen.name)})
    Spacer(modifier = Modifier.height(10.dp))
    SubmitButton(
        textId = "Create Account",
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White), onClick = {
            navController.navigate(RYDXScreens.RegisterScreen.name)})
}

@Composable
fun Logo() {
    Text(
        text = "Explore the App",
        style = MaterialTheme.typography.h3,
        color = Color.Black
    )
}

@Composable
fun SubmitButton(textId: String,
                 colors:ButtonColors,
                 onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        elevation =  ButtonDefaults.elevation(
            defaultElevation = 10.dp,
            pressedElevation = 15.dp,
            disabledElevation = 0.dp),
        colors =colors
    ) {
        if(textId == "Create Account")
        Text(text = textId,color = Color.Black, modifier = Modifier.padding(5.dp))
        else Text(text = textId,color = Color.White, modifier = Modifier.padding(5.dp))
    }

}