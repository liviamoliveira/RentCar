package dev.localiza.rentcar.ui.reservas.listarReservas

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.localiza.rentcar.model.Reserva

class ListarReservasViewModel : ViewModel() {

    val selecaoMinhasReservas = MutableLiveData<Reserva>()

    fun selecaoMinhasReservas(reserva: Reserva) {
        selecaoMinhasReservas.value = reserva
    }
}