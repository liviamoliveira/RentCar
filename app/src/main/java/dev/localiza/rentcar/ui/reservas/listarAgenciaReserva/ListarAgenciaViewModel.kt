package dev.localiza.rentcar.ui.reservas.listarAgenciaReserva

import androidx.lifecycle.MutableLiveData
import dev.localiza.rentcar.base.BaseViewModel
import dev.localiza.rentcar.model.Agencia

class ListarAgenciaViewModel  : BaseViewModel() {

    val selecaoAgencia = MutableLiveData<Agencia>()

    fun selecaoAgencia(agencia: Agencia) {
        selecaoAgencia.value = agencia
    }
}