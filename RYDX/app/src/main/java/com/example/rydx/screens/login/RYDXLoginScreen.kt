package com.example.rydx.screens.login

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rydx.navigation.RYDXScreens
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

@Preview
@Composable
fun  RYDXLoginScreen(navController: NavController = NavController(context = LocalContext.current)){
    val stateIndication = rememberSaveable {
        mutableStateOf("")
    }
    val phoneNumber = rememberSaveable {
        mutableStateOf("")
    }
    val otp = remember {
        mutableStateOf("")
    }
    val verificationID = remember {
        mutableStateOf("")
    }
    val message = remember {
        mutableStateOf("")
    }

    Surface(modifier = Modifier.fillMaxSize(),color= Color.White) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            BackButton(navController)
            Spacer(modifier = Modifier.height(25.dp))
            Logo()
            Spacer(modifier = Modifier.height(100.dp))
            PhoneNumberInput(stateIndication, phoneNumber)
        }
        ToOtp(otp,navController,verificationID,mAuth,message)

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Submit(phoneNumber, navController, stateIndication)
            Spacer(modifier = Modifier.height(20.dp))
            Register(navController)
        }
    }
}

@Composable
private fun Submit(
    phoneNumber: MutableState<String>,
    navController: NavController,
    stateIndication: MutableState<String>
) {
    SubmitButton("Login",
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
        onClick = {
            if (TextUtils.isEmpty(phoneNumber.value)) {
                Toast.makeText(
                    navController.context,
                    "Please enter phone number..",
                    Toast.LENGTH_SHORT
                ).show()
            } else {

                val number = stateIndication.value + phoneNumber.value
                //navController.navigate(RYDXScreens.OTPVerificationScreen.name)
                sendVerificationCode(number, mAuth, navController.context as Activity, callbacks)
            }
        }
    )
}

@Composable
private fun BackButton(navController: NavController) {
    Button(
        onClick = { navController.navigate(RYDXScreens.OpeningScreen.name) },
        modifier = Modifier
            .padding(5.dp)
            .width(40.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        contentPadding = PaddingValues(1.dp)
    ) {
        Text(
            text = "<", color = Color.Black,
            fontSize = 30.sp, fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun PhoneNumberInput(
    stateIndication: MutableState<String>,
    phoneNumber: MutableState<String>
) {
    Row {
        TextField(
            value = stateIndication.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = { stateIndication.value = it },
            placeholder = { Text(text = "C") },
            modifier = Modifier
                .padding(10.dp)
                .width(70.dp),
            textStyle = TextStyle(fontSize = 18.sp),
            singleLine = true,
        )
        TextField(
            value = phoneNumber.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { phoneNumber.value = it },
            placeholder = { Text(text = "Enter your phone number") },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(fontSize = 18.sp),
            singleLine = true,
        )
    }
}

@Composable
fun ToOtp(otp: MutableState<String>, navController: NavController,
    verificationID: MutableState<String>, mAuth: FirebaseAuth,
    message: MutableState<String>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        OtpFields(otp, navController, verificationID, mAuth, message)
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                // on below line updating message
                // and displaying toast message
                message.value = "Verification successful"
                Toast.makeText(navController.context, "Verification successful..", Toast.LENGTH_SHORT).show()
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                // on below line displaying error as toast message.
                message.value = "Fail to verify user : \n" + p0.message
                Toast.makeText(navController.context, "Verification failed..", Toast.LENGTH_SHORT).show()
            }

            override fun onCodeSent(verificationId: String, p1: PhoneAuthProvider.ForceResendingToken) {
                // this method is called when code is send
                super.onCodeSent(verificationId, p1)
                verificationID.value = verificationId
            }
        }
    }
}

@Composable
private fun OtpFields(
    otp: MutableState<String>,
    navController: NavController,
    verificationID: MutableState<String>,
    mAuth: FirebaseAuth,
    message: MutableState<String>
) {
    TextField(
        // on below line we are specifying
        // value for our course duration text field.
        value = otp.value,
        //specifying key board on below line.
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        // on below line we are adding on
        // value change for text field.
        onValueChange = { otp.value = it },

        // on below line we are adding place holder
        // as text as "Enter your course duration"
        placeholder = { Text(text = "Enter your otp") },

        // on below line we are adding modifier to it
        // and adding padding to it and filling max width
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),

        // on below line we are adding text style
        // specifying color and font size to it.
        textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),

        // on below line we are adding
        // single line to it.
        singleLine = true,
    )

    // adding spacer on below line.
    Spacer(modifier = Modifier.height(10.dp))

    // on below line creating button to add
    // data to firebase firestore database.
    Button(
        onClick = {
            // on below line we are validating
            // user input parameters.
            if (TextUtils.isEmpty(otp.value)) {
                // displaying toast message on below line.
                Toast.makeText(navController.context, "Please enter otp..", Toast.LENGTH_SHORT)
                    .show()
            } else {
                // on below line generating phone credentials.
                val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(
                    verificationID.value, otp.value
                )
                // on below line signing within credentials.
                signInWithPhoneAuthCredential(
                    credential,
                    mAuth,
                    navController.context as Activity,
                    navController.context,
                    message
                )
            }
        },
        // on below line we are
        // adding modifier to our button.
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        // on below line we are adding text for our button
        Text(text = "Verify OTP", modifier = Modifier.padding(5.dp))
    }

    // on below line adding spacer.
    Spacer(modifier = Modifier.height(5.dp))

    Text(
        // on below line displaying message for verification status.
        text = message.value,
        style = TextStyle(color = Color.Green, fontSize = 20.sp, fontWeight = FontWeight.Bold)
    )
}

@Composable
private fun Register(navController: NavController) {
    Row(modifier = Modifier.padding(15.dp)) {
        Text(text = "Don't have an account?", color = Color.Black)
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
        onClick =onClick,
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

// on below line creating method to
// sign in with phone credentuals.
private fun signInWithPhoneAuthCredential(
    credential: PhoneAuthCredential,
    auth: FirebaseAuth,
    activity: Activity,
    context: Context,
    message: MutableState<String>
) {
    // on below line signing with credentials.
    auth.signInWithCredential(credential)
        .addOnCompleteListener(activity) { task ->

            if (task.isSuccessful) {
                message.value = "Verification successful"
                Toast.makeText(context, "Verification successful..", Toast.LENGTH_SHORT).show()
            } else {
                if (task.exception is FirebaseAuthInvalidCredentialsException) {

                    Toast.makeText(
                        context,
                        "Verification failed.." + (task.exception as FirebaseAuthInvalidCredentialsException).message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
}


private fun sendVerificationCode(
    number: String,
    auth: FirebaseAuth,
    activity: Activity,
    callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
) {
    // on below line generating options for verification code
    val options = PhoneAuthOptions.newBuilder(auth)
        .setPhoneNumber(number) // Phone number to verify
        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
        .setActivity(activity) // Activity (for callback binding)
        .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
        .build()
    PhoneAuthProvider.verifyPhoneNumber(options)
}

