package com.example.rydx.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rydx.navigation.RYDXScreens

@Preview
@Composable
fun  RYDXLoginScreen(navController: NavController = NavController(context = LocalContext.current)){

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
            Spacer(modifier = Modifier.height(100.dp))
            UserForm()
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            SubmitButton("Login",
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black), onClick = {
                    navController.navigate(RYDXScreens.OTPVerificationScreen.name)})

            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.padding(15.dp)) {
                Text(text = "Don't have an account?",color = Color.Black)
                Text("Register Now",
                    modifier = Modifier
                        .clickable {
                            navController.navigate(RYDXScreens.RegisterScreen.name)
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
        text = "Welcome! Glad to see you ,Again",
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

@Composable
fun UserForm() {
    val stateIndication = rememberSaveable {
        mutableStateOf("")
    }
    val phoneNumber = rememberSaveable {
        mutableStateOf("")
    }
    val valid = remember(stateIndication.value,phoneNumber.value) {
        stateIndication.value.trim().isNotEmpty() && phoneNumber.value.trim().isNotEmpty()
    }


    Row() {
        stateIndicationInput(stateIndication = stateIndication, onAction = KeyboardActions {
        })

        NumberInput(numberState = phoneNumber, onAction = KeyboardActions {
        })

    }
}

@Composable
fun stateIndicationInput(
    stateIndication: MutableState<String>,
    labelId: String = ".",
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    InputField(
        modifier=Modifier.padding(10.dp).width(70.dp),
        valueState = stateIndication,
        labelId = labelId,
        keyboardType = KeyboardType.Text,
        imeAction = imeAction,
        onAction = onAction
    )
}
@Composable
fun NumberInput(
    numberState: MutableState<String>,
    labelId: String = "Phone Number",
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    InputField(
        modifier = Modifier.padding(10.dp).fillMaxWidth(),
        valueState = numberState,
        labelId = labelId,
        keyboardType = KeyboardType.Text,
        imeAction = imeAction,
        onAction = onAction
    )
}


@Composable
fun InputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(modifier=modifier,
        value = valueState.value, onValueChange = { valueState.value = it },
        label = { Text(text = labelId) },
        singleLine = isSingleLine,
        textStyle = TextStyle(fontSize = 18.sp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction
    )
}


