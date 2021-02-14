package dev.localiza.rentcar.ui.reservas.detalharReservas

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.localiza.rentcar.model.Veiculo

class DetalharReservasViewModel : ViewModel() {

    val exibirVeiculo = MutableLiveData<Veiculo>()

    fun exibirVeiculo(veiculo: Veiculo) {
        exibirVeiculo.value = veiculo
    }
}
