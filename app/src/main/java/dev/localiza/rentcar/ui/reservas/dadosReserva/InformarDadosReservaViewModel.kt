package dev.localiza.rentcar.ui.reservas.dadosReserva

import androidx.lifecycle.MutableLiveData
import dev.localiza.rentcar.base.BaseViewModel
import dev.localiza.rentcar.base.extension.SingleLiveEvent
import dev.localiza.rentcar.model.*
import dev.localiza.rentcar.model.Horario
import dev.localiza.rentcar.usecase.ReservarUseCase


internal class InformarDadosReservaViewModel(private val useCase: ReservarUseCase) : BaseViewModel() {

    val agenciaSelecionada = MutableLiveData<Agencia>()
    val dataHoraRetirada = MutableLiveData<Horario>()
    val dataHoraDevolucao= MutableLiveData<Horario>()
    val veiculoSelecionado = MutableLiveData<Veiculo>()

    val sucessoReserva = SingleLiveEvent<Int>()

    fun init(agencia: Agencia?, dataRetirada: Horario, dataDevolucao: Horario, veiculo: Veiculo?) {
        agenciaSelecionada.value = agencia
        dataHoraRetirada.value = dataRetirada
        dataHoraDevolucao.value = dataDevolucao
        veiculoSelecionado.value = veiculo
    }

    fun getAgencia(): Agencia? {
        return agenciaSelecionada.value
    }

    fun getVeiculo(): Veiculo?{
        return veiculoSelecionado.value
    }

    fun getDataRetirada(): Horario?{
        return dataHoraRetirada.value
    }

    fun getDataDevolucao(): Horario?{
        return dataHoraDevolucao.value
    }

    fun salvarReserva(reserva : Reserva){
        doAsync {
            val reserva = useCase.execute(reserva)
            sucessoReserva.value = reserva.id
        }
    }
}
