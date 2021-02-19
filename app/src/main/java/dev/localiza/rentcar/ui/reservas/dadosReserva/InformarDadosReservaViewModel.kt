package dev.localiza.rentcar.ui.reservas.dadosReserva

import androidx.lifecycle.MutableLiveData
import dev.localiza.rentcar.base.BaseViewModel
import dev.localiza.rentcar.model.Agencia
import dev.localiza.rentcar.model.Horario



internal class InformarDadosReservaViewModel : BaseViewModel() {

    val exibirDadosLocalAgencia = MutableLiveData<Agencia>()
    val dataHoraRetirada = MutableLiveData<Horario>()
    val dataHoraDevolucao= MutableLiveData<Horario>()

    fun init(agencia: Agencia?, dataRetirada: Horario, dataDevolucao: Horario) {
        exibirDadosLocalAgencia.value = agencia
        dataHoraRetirada.value = dataRetirada
        dataHoraDevolucao.value = dataDevolucao
    }

}
