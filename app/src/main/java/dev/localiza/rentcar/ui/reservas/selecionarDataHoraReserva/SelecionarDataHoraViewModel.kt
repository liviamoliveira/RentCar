package dev.localiza.rentcar.ui.reservas.selecionarDataHoraReserva

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.localiza.rentcar.model.Agencia
import dev.localiza.rentcar.model.Horario
import dev.localiza.rentcar.model.MovimentoTipo
import java.util.*

internal class SelecionarDataHoraViewModel : ViewModel(){

    val agenciaSelecionada = MutableLiveData<Agencia>()

    private val _horarioRetirada = MutableLiveData<Horario>().apply { value = null }
    private val _horarioDevolucao = MutableLiveData<Horario>().apply { value = null }

    val exibirHorarioRetirada = MutableLiveData<String>()
    val exibirHorarioDevolucao = MutableLiveData<String>()

    val exibirDataRetirada = MutableLiveData<Date>()
    val exibirDataDevolucao = MutableLiveData<Date>()

    private val _dataRetirada = MutableLiveData<Date>().apply { value = null }
    private val _dataDevolucao = MutableLiveData<Date>().apply { value = null }


    fun init(agencia: Agencia) {
        agenciaSelecionada.value = agencia
    }

    fun getAgenciaSelecionada(): Agencia? {
       return agenciaSelecionada.value
    }

    fun getData(movimento: MovimentoTipo): Date? =
            if (movimento == MovimentoTipo.RETIRADA) _dataRetirada.value else _dataDevolucao.value

    fun getHorario(movimento: MovimentoTipo): Horario? =
            if (movimento == MovimentoTipo.RETIRADA) _horarioRetirada.value else _horarioDevolucao.value

    fun setHorario(movimento: MovimentoTipo, horario: Horario?) {
        when (movimento) {
            MovimentoTipo.RETIRADA -> {
                _horarioRetirada.value = horario
                _horarioDevolucao.value = null

                if(horario != null) exibirHorarioRetirada.value = horario?.exibicao
            }
            else -> {
                _horarioDevolucao.value = horario

                if(horario != null) exibirHorarioDevolucao.value = horario?.exibicao
            }
        }
    }

    fun setData(movimento: MovimentoTipo, data: Date?) {
        when (movimento) {
            MovimentoTipo.RETIRADA -> {
                _dataRetirada.value = data
                setData(MovimentoTipo.DEVOLUCAO, null)

                if(data != null) exibirDataRetirada.value = data
            }
            else -> {
                _dataDevolucao.value = data

                if(data != null) exibirDataDevolucao.value = data
            }
        }
        setHorario(movimento, null)
    }
}