package dev.localiza.rentcar.ui.reservas.detalharReservas

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.localiza.rentcar.model.Veiculo

class DetalharReservasViewModel : ViewModel() {

    val exibirVeiculo = MutableLiveData<Veiculo>()
    val exibirBotao = MutableLiveData<Boolean>()

    fun init(veiculo: Veiculo, visibilidadeBotao: Boolean) {
        exibirVeiculo.value = veiculo
        exibirBotao.value = visibilidadeBotao
    }
}
