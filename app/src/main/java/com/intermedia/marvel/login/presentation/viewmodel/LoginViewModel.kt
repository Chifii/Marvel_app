package com.intermedia.marvel.login.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {
    private val errorMDL: MutableLiveData<Boolean> = MutableLiveData()
    val error get() = errorMDL as LiveData<Boolean>

    private val loginOkMDL: MutableLiveData<Boolean> = MutableLiveData()
    val loginOk get() = loginOkMDL as LiveData<Boolean>

    fun setupLoginButton(user: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(
            user, password
        ).addOnCompleteListener {
            if (it.isSuccessful) {
                loginOkMDL.value = true
            } else {
                errorMDL.value = true
            }
        }
    }
}