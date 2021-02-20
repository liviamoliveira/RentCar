package dev.localiza.rentcar.ui.reservas.listarAgenciaReserva

import androidx.lifecycle.MutableLiveData
import dev.localiza.rentcar.base.BaseViewModel
import dev.localiza.rentcar.base.extension.SingleLiveEvent
import dev.localiza.rentcar.model.Agencia
import dev.localiza.rentcar.usecase.BuscarAgenciasUseCase

internal class ListarAgenciaViewModel(private val useCase: BuscarAgenciasUseCase)  : BaseViewModel() {

    val selecaoAgencia = MutableLiveData<Agencia>()
    val sucessoListaAgencias = SingleLiveEvent<List<Agencia>>()

    fun selecaoAgencia(agencia: Agencia) {
        selecaoAgencia.value = agencia
    }


    fun buscarAgencias() {
        doAsync {
            val listaAgencias = useCase.execute(Unit)
            sucessoListaAgencias.value = listaAgencias
        }
    }
}