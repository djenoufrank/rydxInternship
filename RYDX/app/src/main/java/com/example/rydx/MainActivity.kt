package com.example.rydx

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.rydx.navigation.RYDXNavigation
import com.example.rydx.ui.theme.RYDXTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RYDXTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RYDXApp()
                }
            }
        }
    }
//    fun toast() {
//        if (!isNetworkAvailable()) {
//            Toast.makeText(this, "you are not longer connected", Toast.LENGTH_SHORT).show()
//        }
//    }
    fun isNetworkAvailable(): Boolean {
        return isNetworkAvailable(this)
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nw = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            //for other device how are able to connect with Ethernet
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            //for check internet over Bluetooth
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false
        }
    }

    fun isValidPhoneNumber(phoneNumber: String): Boolean {
        if (phoneNumber.length < 10 || phoneNumber.length > 12) return false
        if (phoneNumber[0] != '+') return false

        for (i in 1 until phoneNumber.length) {
            if (!Character.isDigit(phoneNumber[i])) {
                return false
            }
        }
        return true
    }
}

@Composable
fun RYDXApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        RYDXNavigation()
    }
}


