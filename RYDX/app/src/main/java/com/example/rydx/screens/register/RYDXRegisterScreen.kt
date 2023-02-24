package com.example.rydx.screens.register

import android.util.Patterns
import android.widget.Toast
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rydx.R
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
            Spacer(modifier = Modifier.height(55.dp))
            UserForm(navController)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {

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

@Composable
fun UserForm(navController: NavController) {
    val email = rememberSaveable {
        mutableStateOf("")
    }
    val userName = rememberSaveable {
        mutableStateOf("")
    }
    val phoneNumber = rememberSaveable {
        mutableStateOf("")
    }
    val valid = remember(email.value, userName.value,phoneNumber.value) {
        email.value.trim().isNotEmpty() && userName.value.trim().isNotEmpty()
                && phoneNumber.value.trim().isNotEmpty() && Patterns.EMAIL_ADDRESS
            .matcher(email.value).matches()
    }
    Column(Modifier
        .height(250.dp)
        .background(MaterialTheme.colors.background)
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally) {

    UserNameInput(nameState = userName, onAction = KeyboardActions {
    })
    NumberInput(numberState = phoneNumber, onAction = KeyboardActions {
    })
    EmailInput(emailState = email, onAction = KeyboardActions {
    })
    }

    SubmitButton("Register",
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black), onClick = {
            if(valid){
                navController.navigate(RYDXScreens.OTPVerificationScreen.name)
            }else{
                Toast.makeText(navController.context,
                    "Please check your entries!",
                    Toast.LENGTH_LONG).show()
            }
        }

    )
}
@Composable
fun EmailInput(
    modifier: Modifier = Modifier,
    emailState: MutableState<String>,
    labelId: String = "E-mail",
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    InputField(
        modifier = modifier,
        valueState = emailState,
        labelId = labelId,
        keyboardType = KeyboardType.Email,
        imeAction = imeAction,
        onAction = onAction
    )
}
@Composable
fun NumberInput(
    modifier: Modifier = Modifier,
    numberState: MutableState<String>,
    labelId: String = "Phone Number",
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    InputField(
        modifier = modifier,
        valueState = numberState,
        labelId = labelId,
        keyboardType = KeyboardType.Text,
        imeAction = imeAction,
        onAction = onAction
    )
}

@Composable
fun UserNameInput(
    modifier: Modifier = Modifier,
    nameState: MutableState<String>,
    labelId: String = "User Name",
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    InputField(
        modifier = modifier,
        valueState = nameState,
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
    OutlinedTextField(
        value = valueState.value, onValueChange = { valueState.value = it },
        label = { Text(text = labelId) },
        singleLine = isSingleLine,
        textStyle = TextStyle(fontSize = 18.sp),
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction
    )
}


