package com.example.rydx.viewModels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.rydx.models.User
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject

class CloudFirebaseUserViewModel : ViewModel() {
    private val database = Firebase.firestore
    private val usersValues = MutableLiveData<List<User>>()
    private val userIsKnow = MutableLiveData<List<User>>()
    private lateinit var usersRegistration: ListenerRegistration


    fun connexionUsersValueChanges(number: String): Boolean {
        val docRef = database.collection("users").document(number)
        return docRef.id == number
    }

    fun stopListeningForUserChanges() = usersRegistration.remove()
    fun onUsersValuesChange(): LiveData<List<User>> {
        listenForUsersValueChanges()
        return usersValues
    }

    private fun listenForUsersValueChanges() {
        usersRegistration = database.collection("Users")
            .addSnapshotListener(EventListener { value, error ->
                if (error != null || value == null) {
                    return@EventListener
                }

                if (value.isEmpty) {
                    usersValues.postValue(emptyList())
                } else {
                    val users = ArrayList<User>()
                    for (doc in value) {
                        val user = doc.toObject(User::class.java)
                        users.add(user)
                    }
                    usersValues.postValue(users)
                }
            })
    }

    fun createUser(user: User, navController: NavController) {
        val db = Firebase.firestore
        user.phoneNumber?.let {
            db.collection("users").document(it).set(user)
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
