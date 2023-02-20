package com.example.rydx.screens.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rydx.navigation.RYDXScreens

@Preview
@Composable
fun RYDXRegisterScreen(navController: NavController = NavController(context = LocalContext.current)){

    Surface(modifier = Modifier.fillMaxSize(),color= Color.White) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Button(
                onClick = { navController.navigate(RYDXScreens.OpeningScreen.name)},
                modifier = Modifier
                    .padding(5.dp)
                    .width(40.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                contentPadding = PaddingValues(1.dp)
            ) {
                Text(text = "<",color = Color.Black,
                fontSize =30.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(25.dp))
            Logo()
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            SubmitButton("Register",
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black), onClick = {
                    navController.navigate(RYDXScreens.OTPVerificationScreen.name)})

            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.padding(15.dp)) {
                Text(text = "Already have an account?",color = Color.Black)
                Text("LogIn Now",
                    modifier = Modifier
                        .clickable {
                            navController.navigate(RYDXScreens.LoginScreen.name)
                        }
                        .padding(start = 5.dp),
                    fontWeight = FontWeight.Bold,
                    color = Color.Black)
            }
        }
    }

}


@Composable
fun Logo() {
    Text(
        text = "Hello! Register to get started",
        style = MaterialTheme.typography.h4,
        color = Color.Black
    )
}

@Composable
fun SubmitButton(textId: String,
                 colors: ButtonColors,
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
            Text(text = textId,color = Color.White, modifier = Modifier.padding(5.dp))
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserForm() {
    val email = rememberSaveable {
        mutableStateOf("")
    }
    val userName = rememberSaveable {
        mutableStateOf("")
    }
    val phoneNumber = rememberSaveable {
        mutableStateOf(0)
    }
    val valid = remember(email.value, userName.value,phoneNumber.value) {
        email.value.trim().isNotEmpty() && userName.value.trim().isNotEmpty()
                && phoneNumber.value!=0
    }



}