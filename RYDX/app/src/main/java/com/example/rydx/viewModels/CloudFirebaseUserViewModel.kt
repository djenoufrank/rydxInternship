package com.example.rydx.viewModels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.rydx.models.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class CloudFirebaseUserViewModel : ViewModel() {
    private val database = Firebase.firestore
    fun connexionUser(number: String):Boolean{
        val docRef = database.collection("users").document(number)
        return docRef.id == number
    }

    fun createUser(user: User, navController: NavController) {
        user.phoneNumber?.let {
            database.collection("users").document(it).set(user)
                .addOnSuccessListener {
                    Toast.makeText(
                        navController.context,
                        "Account created successfully!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(
                        navController.context,
                        "Error adding user!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }
}
