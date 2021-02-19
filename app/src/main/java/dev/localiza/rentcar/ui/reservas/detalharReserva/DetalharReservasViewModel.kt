package dev.localiza.rentcar.ui.reservas.detalharReserva

import androidx.lifecycle.MutableLiveData
import dev.localiza.rentcar.model.Veiculo
import dev.localiza.rentcar.base.BaseViewModel
import dev.localiza.rentcar.model.Agencia
import dev.localiza.rentcar.model.Cliente
import dev.localiza.rentcar.model.Horario
import dev.localiza.rentcar.usecase.BuscarClienteUseCase
import java.text.SimpleDateFormat
import java.util.*

internal class DetalharReservasViewModel(private val useCase: BuscarClienteUseCase) : BaseViewModel() {

    val exibirVeiculo = MutableLiveData<Veiculo>()
    val exibirBotao = MutableLiveData<Boolean>()
    val exibirDadosLocalAgencia = MutableLiveData<Agencia>()

    val dataHoraRetiradaTexto = MutableLiveData<String>()
    val dataHoraDevolucaoTexto= MutableLiveData<String>()
    val localRetiradaTexto = MutableLiveData<String>()
    val localDevolucaoTexto= MutableLiveData<String>()

    val buscarClienteSucesso = MutableLiveData<Cliente>()

    private val formatData = "dd MMMM yyyy  HH:mm"

    fun init(veiculo: Veiculo, visibilidadeBotao: Boolean, agencia: Agencia?, dataRetirada: Horario?, dataDevolucao: Horario?) {
        exibirVeiculo.value = veiculo
        exibirBotao.value = visibilidadeBotao
        exibirDadosLocalAgencia.value = agencia
        localRetiradaTexto.value = agencia?.nome
        localDevolucaoTexto.value = agencia?.nome

        val simpleDateFormat = SimpleDateFormat(formatData, Locale.getDefault())
        val dataHoraRetirada = simpleDateFormat.format(dataRetirada?.dataHora)
        val dataHoraDevolucao = simpleDateFormat.format(dataDevolucao?.dataHora)

        dataHoraRetiradaTexto.value = dataHoraRetirada
        dataHoraDevolucaoTexto.value = dataHoraDevolucao
    }

    fun initDetalhesReserva(veiculo: Veiculo, visibilidadeBotao: Boolean, dataRetirada: String, dataDevolucao: String, localRetirada: String, localDevolucao: String) {
        exibirVeiculo.value = veiculo
        exibirBotao.value = visibilidadeBotao
        localRetiradaTexto.value = localRetirada
        localDevolucaoTexto.value = localDevolucao
        dataHoraRetiradaTexto.value = dataRetirada
        dataHoraDevolucaoTexto.value = dataDevolucao
    }


    fun getVeiculo(): Veiculo? {
        return exibirVeiculo.value
    }

    fun getBuscarCliente(codigo: Int) {
        doAsync {
            val response = useCase.execute(codigo)
            buscarClienteSucesso.value = response
        }
    }

    fun getCliente(): Cliente? {
        return buscarClienteSucesso.value
    }
}
