package com.example.citas

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class RegistrarCita : AppCompatActivity() {

    private lateinit var datePickerButton: Button
    private lateinit var fechaTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_cita)

        datePickerButton = findViewById(R.id.btnSeleccionarFecha)
        fechaTextView = findViewById(R.id.tv_fecha)

        datePickerButton.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                // Obtener la fecha seleccionada
                val selectedDate = "$dayOfMonth/${month + 1}/$year"
                // Actualiza el TextView con la fecha seleccionada
                fechaTextView.text = selectedDate
            },
            year,
            month,
            dayOfMonth
        )

        datePickerDialog.show()
    }
}
