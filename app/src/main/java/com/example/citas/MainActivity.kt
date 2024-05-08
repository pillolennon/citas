package com.example.citas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var btnLogin: Button
    private lateinit var txtEmail: EditText
    private lateinit var txtPassword: EditText
    private lateinit var tv_go_to_register: TextView

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Variables
        btnLogin = findViewById(R.id.btnLogin)
        txtEmail = findViewById(R.id.txtEmail)
        txtPassword = findViewById(R.id.txtPassword)
        tv_go_to_register = findViewById(R.id.tv_go_to_register)

        // Configurar click listener para el textView de Registro de usuarios
        tv_go_to_register.setOnClickListener{
            goToRegister()
        }

        // Configurar click listener para el botón de inicio de sesión
        btnLogin.setOnClickListener {
            val email = txtEmail.text.toString()
            val password = txtPassword.text.toString()

            // Verificar si los campos están vacíos
            if (email.isEmpty() || password.isEmpty()) {
                // Mostrar un mensaje al usuario para llenar todos los campos
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                // Si los campos no están vacíos, intentar iniciar sesión
                signIn(email, password)
            }
        }
    }

    //FUNCION PARA IR AL REGISTRO DE USUARIOS
    private fun goToRegister(){
        val intent = Intent(this, RegisterMainActivity2::class.java)
        startActivity(intent)
        finish()
    }

    private fun signIn(email: String, password: String) {
        // Autenticar el usuario con correo electrónico y contraseña
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Inicio de sesión exitoso
                    Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                    // Aquí puedes redirigir al usuario a la actividad principal u otra pantalla
                    val i = Intent(this, MenuActivity::class.java)
                    startActivity(i)
                    finish()
                } else {
                    // Error al iniciar sesión
                    if (task.exception?.message == "La dirección de correo electrónico no es correcta.") {
                        // Correo electrónico mal formateado
                        Toast.makeText(
                            this,
                            "El correo electrónico no es correcto.",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (task.exception?.message == "No hay ningún registro de usuario correspondiente a este identificador.") {
                        // No hay usuario registrado con ese correo electrónico
                        Toast.makeText(
                            this,
                            "El usuario no existe, por favor regístrese",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        // Otro error de inicio de sesión
                        Toast.makeText(
                            this,
                            "Error al iniciar sesión.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
    }
}
