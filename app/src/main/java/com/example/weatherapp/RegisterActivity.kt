package com.example.weatherapp


import android.R.attr.password
import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.ui.components.inputs.DataInput
import com.example.weatherapp.ui.components.inputs.PasswordInput
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

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
    val activity = LocalActivity.current

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

        DataInput(
            state = nameUser,
            label = "Digite um nome de usuário",
            modifier = modifier
        ) { nameUser = it }

        DataInput(
            state = emailUser,
            label = "Digite um e-mail",
            modifier = modifier
        ) { emailUser = it }

        PasswordInput(
            state = passwordUser,
            label = "Digite uma senha para sua conta",
            modifier = modifier
        ) { passwordUser = it }

        PasswordInput(
            state = passwordConfirm,
            label = "Digite a senha novamente",
            modifier = modifier
        ) { passwordConfirm = it }

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Button(onClick =
                {
                    Firebase.auth.createUserWithEmailAndPassword(emailUser, passwordUser)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(activity,
                                    "Registro OK!", Toast.LENGTH_LONG).show()
                            } else {
                                Toast.makeText(activity,
                                    "Registro FALHOU!", Toast.LENGTH_LONG).show()
                            }
                        }

                },
                enabled = (nameUser.isNotEmpty() && emailUser.isNotEmpty() && passwordUser.isNotEmpty()) && (passwordUser== passwordConfirm)
            ) {
                Text("Registrar")
            }

            Button(onClick =
                {
                    activity?.finish()
                }
            ) {
                Text("Cancelar")
            }


            Button(
                onClick = {
                    nameUser = ""
                    emailUser = ""
                    passwordUser = ""
                    passwordConfirm = ""
                }
            )
            {
                Text("Limpar")
            }
        }
    }

}