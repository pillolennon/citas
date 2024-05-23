package com.example.citas

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar


class RegistrarCita : AppCompatActivity() {

    private lateinit var datePickerButton: Button
    private lateinit var fechaTextView: TextView
    private lateinit var btnPickTime: Button
    private lateinit var tvSelectedTime: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_cita)


        //VARIABLES
        datePickerButton = findViewById(R.id.btnSeleccionarFecha)
        fechaTextView = findViewById(R.id.tv_fecha)
        btnPickTime = findViewById(R.id.btnPickTime)
        tvSelectedTime = findViewById(R.id.tvSelectedTime)



        //LISTENER AL HACER CLICK EN EL BOTON SELECCIONAR FECHA
        datePickerButton.setOnClickListener {
            showDatePickerDialog()
        }

        //LISTENER AL HACER CLICK EN EL BOTON SELECCIONAR HORA
        btnPickTime.setOnClickListener {
            showTimePickerDialog()
        }



    }



    //FUNCION QUE MUESTRA EL CALENDARIO
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
    //FUNCION QUE MUESTRA LA HORA
    private fun showTimePickerDialog() {
        val cal = Calendar.getInstance()
        val hour = cal.get(Calendar.HOUR_OF_DAY)
        val minute = cal.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            TimePickerDialog.OnTimeSetListener { _: TimePicker?, hourOfDay: Int, minute: Int ->
                // Determinar si es AM o PM
                val amPm: String
                val hourOfDayFormatted: Int
                if (hourOfDay >= 12) {
                    amPm = "PM"
                    hourOfDayFormatted = if (hourOfDay == 12) hourOfDay else hourOfDay - 12
                } else {
                    amPm = "AM"
                    hourOfDayFormatted = if (hourOfDay == 0) 12 else hourOfDay
                }

                // Formatear la hora seleccionada
                val selectedTime = String.format("%02d:%02d %s", hourOfDayFormatted, minute, amPm)

                // Actualizar el TextView con la hora seleccionada
                tvSelectedTime.text = selectedTime
            },
            hour,
            minute,
            false // Cambiado a false para usar el formato de 12 horas
        )

        timePickerDialog.show()
    }
}
