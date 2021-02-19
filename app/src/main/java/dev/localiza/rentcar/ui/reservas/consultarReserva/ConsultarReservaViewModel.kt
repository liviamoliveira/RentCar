package dev.localiza.rentcar.ui.reservas.consultarReserva

import androidx.lifecycle.MutableLiveData
import dev.localiza.rentcar.base.BaseViewModel
import dev.localiza.rentcar.model.Reserva
import dev.localiza.rentcar.usecase.BuscarReservaUseCase

internal class ConsultarReservaViewModel(private val useCase: BuscarReservaUseCase) : BaseViewModel() {

    val buscarReservaSucesso = MutableLiveData<List<Reserva>>()

    fun getBuscarReservaCPF(cpf: String) {
        doAsync {
            val response = useCase.execute(cpf)
            buscarReservaSucesso.value = response
        }
    }
}