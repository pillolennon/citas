package com.example.citas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException

class RegisterMainActivity2 : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var txtEmail: EditText
    private lateinit var txtPassword: EditText
    private lateinit var btnRegistrarse: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_main2)

        // Inicializar FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Obtener referencias de vistas
        txtEmail = findViewById(R.id.txtEmail)
        txtPassword = findViewById(R.id.txtPassword)
        btnRegistrarse = findViewById(R.id.btnRegistrarse)

        // Configurar clic en el botón de registro
        btnRegistrarse.setOnClickListener {
            val email = txtEmail.text.toString()
            val password = txtPassword.text.toString()

            // Verificar si los campos de correo electrónico y contraseña están vacíos
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                registrarUsuario(email, password)
            }
        }

        // Variable para volver al login
        val tvGoLogin = findViewById<TextView>(R.id.tv_go_to_login)
        tvGoLogin.setOnClickListener {
            goToLogin()
        }
    }

    // Función para registrar al usuario
    private fun registrarUsuario(email: String, password: String) {
        // Registrar el usuario utilizando Firebase Authentication
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Registro exitoso
                    Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
                    // Puedes redirigir al usuario a otra actividad aquí si lo deseas
                } else {
                    // Error al registrar el usuario
                    if (task.exception is FirebaseAuthUserCollisionException) {
                        // El usuario ya existe
                        Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_SHORT).show()
                    } else {
                        // Otro error
                        Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

    // Función para volver al login
    private fun goToLogin() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
