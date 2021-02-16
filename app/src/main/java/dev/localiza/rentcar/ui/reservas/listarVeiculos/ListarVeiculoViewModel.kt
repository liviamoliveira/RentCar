package dev.localiza.rentcar.ui.reservas.listarVeiculos

import androidx.lifecycle.MutableLiveData
import dev.localiza.rentcar.model.Veiculo
import dev.localiza.rentcar.base.BaseViewModel
import dev.localiza.rentcar.model.Agencia
import dev.localiza.rentcar.model.Horario

internal class EscolherVeiculoViewModel : BaseViewModel(){

    val selecaoVeiculo = MutableLiveData<Veiculo>()
    val agenciaSelecionada = MutableLiveData<Agencia>()
    val dataHoraRetiradaSelecionada = MutableLiveData<Horario>()
    val dataHoraDevolucaoSelecionada = MutableLiveData<Horario>()

    fun selecaoVeiculo(veiculo: Veiculo) {
        selecaoVeiculo.value = veiculo
    }

    fun setAgencia(agencia: Agencia){
        agenciaSelecionada.value = agencia
    }

    fun setDataHoraRetirada(data: Horario){
        dataHoraRetiradaSelecionada.value = data
    }

    fun setDataHoraDevolucao(data: Horario){
        dataHoraDevolucaoSelecionada.value = data
    }

    fun getAgencia(): Agencia? {
        return agenciaSelecionada.value
    }

    fun getDataHoraDevolucao(): Horario? {
        return dataHoraDevolucaoSelecionada.value
    }

    fun getDataHoraRetirada(): Horario? {
        return dataHoraRetiradaSelecionada.value
    }
}