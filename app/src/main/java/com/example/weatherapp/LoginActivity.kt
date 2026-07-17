package com.example.weatherapp

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.core.view.MenuProvider
import com.example.weatherapp.ui.components.inputs.DataInput
import com.example.weatherapp.ui.components.inputs.PasswordInput
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

/*
*   Author: Adriano Eloy Justino da Silva
*
*   Matricula: 20242Y6-RC0093
*
* */


class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginPage()
        }
    }

    override fun addMenuProvider(
        p0: MenuProvider,
        p1: androidx.lifecycle.LifecycleOwner,
        p2: androidx.lifecycle.Lifecycle.State
    ) {
        TODO("Not yet implemented")
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPage (modifier: Modifier = Modifier){
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Bem-vindo/a!",
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(16.dp))


        DataInput(
            state = email,
            label = "Digite seu e-mail",
            modifier = modifier
        ) { email = it }

        PasswordInput(
            state = password,
            label = "Digite sua senha",
            modifier = modifier
        ) { password = it }

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 15.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Button(onClick =
                {
                    Firebase.auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(context, "Login OK!", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(context, "Login FALHOU!", Toast.LENGTH_LONG).show()
                        }
                    }
                },
                enabled = email.isNotEmpty()
            ) {
                Text("Login")
            }
            Button(onClick =
                {
                    val intent = Intent(context, RegisterActivity::class.java).apply {

                        flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
                    }

                    context.startActivity(intent)
                },

            ) {
                Text("Cadastrar")
            }

            Button(
                onClick = { email = ""; password = "" },

            ){
                Text("Limpar")
            }
        }
    }
}