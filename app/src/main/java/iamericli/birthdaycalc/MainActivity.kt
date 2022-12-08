package iamericli.birthdaycalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.text.SimpleDateFormat

import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate : TextView? = null
    private var tvAgeInMinutes : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)

        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }

    }

    private fun clickDatePicker() {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            this,
            { view, selectedYear, selectedMonth, selectedDayOfMonth ->

                Toast.makeText(
                    this,
                    "Year was $selectedYear, month was ${selectedMonth + 1}, day of month was $selectedDayOfMonth",
                    Toast.LENGTH_LONG
                ).show()

                //set selectedDate = selected day from the DatePicker
                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

                //assign the selectedDate to tvSelectedDate and update on the screen
                tvSelectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)

                // " theDate?.let " is used for make sure that we only execute this code if the data is no empty
                theDate?.let {
                    val selectedDateInMinutes = theDate.time /60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                    // " currentDate?.let " is used for make sure that we only execute this code if the data is no empty
                    currentDate?.let {

                        val currentDateInMinutes = currentDate.time / 60000

                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                        tvAgeInMinutes?.text = differenceInMinutes.toString()
                    }

                }


            },
            year,
            month,
            day
        )
        //set the final day for users to select ( yesterday )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }


}

