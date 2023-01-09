package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.widget.AbsListView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.R

import java.text.SimpleDateFormat
import java.util.*

class AgeInMinutes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_age_in_minutes)



        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener {view->
            clickDatePicker(view)
            Toast.makeText(this,"Button works",Toast.LENGTH_LONG).show()
        }
    }
    fun clickDatePicker(view: View){
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

       val dpd =  DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{
                 view,selectedYear,selectedMonth,selectedDayOfMonth ->
            Toast.makeText(this,"The chosen year is $selectedYear,the month is $selectedMonth,the day is $selectedDayOfMonth"
                ,Toast.LENGTH_LONG).show()

               val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

                val tvSelectedDate =  findViewById<TextView>(R.id.tvSelectedDate)
                tvSelectedDate.setText(selectedDate)

                val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)


                val theDate = sdf.parse(selectedDate)

                val selectedDateInMinutes = theDate!!.time / 60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                val currentDateInMinutes = currentDate!!.time / 60000


                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                val tvSelectedDateInMinutes = findViewById<TextView>(R.id.tvSelectedDateInMinutes)
                tvSelectedDateInMinutes.setText(differenceInMinutes.toString())



        }
          ,year
          ,month
          ,day)


        dpd.datePicker.setMaxDate(Date().time-86400000)
        dpd.show()




    }
}