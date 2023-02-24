package com.example.rydx.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rydx.models.User
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.ktx.firestore

class CloudFirebaseUserViewModel : ViewModel() {
    private val database = Firebase.firestore
    private val usersValues = MutableLiveData<List<User>>()
    private val userIsKnow = MutableLiveData<List<User>>()
    private lateinit var usersRegistration: ListenerRegistration


    fun connexionUsersValueChanges(number :String): Boolean{

        usersRegistration = database.collection("users")
            .addSnapshotListener(EventListener { value, error ->
                if (error != null || value == null) {
                    return@EventListener
                }
                if (!value.isEmpty) {
                    val users = ArrayList<User>()
                    for (doc in value) {
                        if(doc.id==number){
                        val user = doc.toObject(User::class.java)
                        users.add(user)
                    }
                        break
                    }
                    userIsKnow.postValue(users)

                    }
            })
        return userIsKnow.value.isNullOrEmpty()
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

}
