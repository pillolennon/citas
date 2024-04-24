package com.example.citas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MenuActivity : AppCompatActivity() {


    private lateinit var btnCerrarSesion: Button
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        //INICIALIZAR FIREBASE
        auth = FirebaseAuth.getInstance()

        //OBTENER REFERENCIA DEL BOTON CERRAR SESION
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion)

        //CONFIGURAR CLIC EN EL BOTON DE CIERRE DE SESION
        btnCerrarSesion.setOnClickListener{
            cerrarSesion()
        }
    }
    //FUCNION PARA CERRAR SESION
    private fun cerrarSesion(){
        //Cerrar la sesion del usuario actual
        auth.signOut()
        //Mostrar Toast indicando que se ha cerrado la sesión
        Toast.makeText(this, "Sesión cerrada exitosamente", Toast.LENGTH_SHORT).show()
        goToLogin()
    }

    //FUNCION PARA IR AL LOGIN DESPUES DE CERRAR SESION
    private fun goToLogin() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}