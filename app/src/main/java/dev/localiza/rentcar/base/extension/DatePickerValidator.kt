package dev.localiza.rentcar.base.extension

import com.google.android.material.datepicker.CalendarConstraints
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
internal class DatePickerValidator(
        private var dataMinima: Date? = null,
        private var dataMaxima: Date? = null
) :
        CalendarConstraints.DateValidator {

    init {
        dataMinima = inicioData(dataMinima)
        dataMaxima = inicioData(dataMaxima)
    }

    override fun isValid(date: Long): Boolean {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = date
            add(Calendar.DAY_OF_YEAR, 1)
        }

        calendar.iniciarTempo()

        return ehMaiorOuIgualDataMinima(calendar)
    }

    private fun ehMaiorOuIgualDataMinima(calendar: Calendar): Boolean {
        if (dataMinima == null) {
            return true
        }
        return calendar.time >= dataMinima
    }
}



