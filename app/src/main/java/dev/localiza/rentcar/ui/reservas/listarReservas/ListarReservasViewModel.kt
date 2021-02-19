package dev.localiza.rentcar.ui.reservas.listarReservas

import androidx.lifecycle.MutableLiveData
import dev.localiza.rentcar.model.Reserva
import dev.localiza.rentcar.base.BaseViewModel
import dev.localiza.rentcar.usecase.BuscarReservaUseCase

internal class ListarReservasViewModel(private val useCase: BuscarReservaUseCase) : BaseViewModel() {

    val selecaoMinhasReservas = MutableLiveData<Reserva>()
    val buscarReservaSucesso = MutableLiveData<List<Reserva>>()

    fun selecaoMinhasReservas(reserva: Reserva) {
        selecaoMinhasReservas.value = reserva
    }

    fun getBuscarReservaCPF(cpf: String) {
        doAsync {
            val response = useCase.execute(cpf)
            buscarReservaSucesso.value = response
        }
    }
}