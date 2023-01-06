package sparsh.projects

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.time.Month
import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity() {
    private var selectedDate : TextView? = null
    private var ageInMinutes : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        selectedDate = findViewById(R.id.selectedDate)
        ageInMinutes = findViewById(R.id.ageInMinutes )
        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }


    }

    private fun  clickDatePicker()
    {
        val myCalander = Calendar.getInstance()
        val year = myCalander.get(Calendar.YEAR)
        val month = myCalander.get(Calendar.MONTH)
        val date = myCalander.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this ,
            {_, year, month, dayOfMonth ->

                Toast.makeText(this,"Thanks for selecting Date" , Toast.LENGTH_LONG).show()

                val sDate =  "$dayOfMonth/${month+1}/$year"
                selectedDate?.text = sDate

                val sdf = SimpleDateFormat("dd/MM/yyy", Locale.ENGLISH)

                val theDate = sdf.parse(sDate)
                theDate?.let {
                    val selectedDateInMinutes = theDate.time / 60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time / 60000

                        val diffrenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                        ageInMinutes?.text= diffrenceInMinutes.toString()
                    }
                }
            },


            year,month,date
        )

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()


    }

}