package dev.localiza.rentcar.ui.reservas.listarReservas

import androidx.lifecycle.MutableLiveData
import dev.localiza.rentcar.model.Reserva
import dev.localiza.rentcar.base.BaseViewModel

class ListarReservasViewModel : BaseViewModel() {

    val selecaoMinhasReservas = MutableLiveData<Reserva>()

    fun selecaoMinhasReservas(reserva: Reserva) {
        selecaoMinhasReservas.value = reserva
    }
}