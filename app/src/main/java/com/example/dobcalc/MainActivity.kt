package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import kotlin.math.log

@Suppress("NAME_SHADOWING")
class MainActivity : AppCompatActivity() {

    private var visibleDate: TextView? = null
    private var visibleRes: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnChoseWeek: Button = findViewById(R.id.button)
        visibleDate = findViewById(R.id.selDate)
        visibleRes = findViewById(R.id.visualWeeks)

        btnChoseWeek.setOnClickListener{
            clickDatepicker()
        }
    }

    private fun clickDatepicker(){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this,
            {view,year,month,dayOfMonth->

                //виводить тост
                Toast.makeText(this, "рік $year", Toast.LENGTH_LONG).show()


                //змінна обраної дати
                val selectedDate = "$dayOfMonth.${month+1}.$year"
                //виводить на екран обрану дату з календаря
                visibleDate?.text = selectedDate

              //створюється шаблон для порядку днів місяця та року в даті
              val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH)

                //змінна форматування тексту обраної дати в формат дати
                val theDate = sdf.parse(selectedDate)

                //змінна з обраної дати вираховуємо кількість тижнів
               val selectedDateInWeeks = (theDate.time/60000)/10080

                //
               val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
               val currentDateInWeeks = (currentDate.time/60000)/10080
                val resInWeeks = currentDateInWeeks - selectedDateInWeeks
               visibleRes?.text = resInWeeks.toString()

            },
            year,
            month,
            day
            ).show()


    }
}