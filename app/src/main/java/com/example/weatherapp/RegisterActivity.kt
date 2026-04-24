package com.example.weatherapp

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            RegisterPage()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPage(modifier: Modifier = Modifier){

    var nameUser by rememberSaveable { mutableStateOf("") }
    var emailUser by rememberSaveable { mutableStateOf("") }
    var passwordUser by rememberSaveable { mutableStateOf("") }
    var passwordConfirm by rememberSaveable { mutableStateOf("") }
    val activity = LocalContext.current as Activity

    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Formulário de Cadastro",
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nameUser,
            label = { Text(text = "Digite seu Nome de Usuário") },
            modifier = modifier.fillMaxWidth(0.75f),
            onValueChange = { nameUser = it }
        )
        OutlinedTextField(
            value = emailUser,
            label = { Text(text = "Digite seu E-mail") },
            modifier = modifier.fillMaxWidth(0.75f),
            onValueChange = { emailUser = it }
        )
        OutlinedTextField(
            value = passwordUser,
            label = { Text(text = "Digite uma Senha para sua Conta") },
            modifier = modifier.fillMaxWidth(0.75f),
            onValueChange = { passwordUser = it },
            visualTransformation = PasswordVisualTransformation()
        )
        OutlinedTextField(
            value = passwordConfirm,
            label = { Text(text = "Digite novamente a Senha") },
            modifier = modifier.fillMaxWidth(0.75f),
            onValueChange = { passwordConfirm = it },
            visualTransformation = PasswordVisualTransformation()
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Button(onClick =
                {
                    Toast.makeText(
                        activity,
                        "Registrado com Sucesso!",
                        Toast.LENGTH_LONG
                    ).show()

                    activity.finish()
                },
                enabled = (nameUser.isNotEmpty() && emailUser.isNotEmpty() && passwordUser.isNotEmpty()) && (passwordUser == passwordConfirm)
            ) {
                Text("Registrar")
            }

            Button(onClick =
                {
                    activity.finish()
                }
            ) {
                Text("Cancelar")
            }


            Button(
                onClick = {nameUser = ""; emailUser = ""; passwordUser = ""; passwordConfirm = ""}
            )
            {
                Text("Limpar")
            }
        }
    }

}