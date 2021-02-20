package dev.localiza.rentcar.ui.cadastro

import androidx.lifecycle.MutableLiveData
import dev.localiza.rentcar.base.BaseViewModel
import dev.localiza.rentcar.base.extension.SingleLiveEvent
import dev.localiza.rentcar.model.Cliente
import dev.localiza.rentcar.usecase.CadastrarClienteUseCase
import java.util.*

internal class CadastroViewModel(private val useCase: CadastrarClienteUseCase) : BaseViewModel() {

    val sucessoCadastro = SingleLiveEvent<Boolean>()
    val dadosCliente = MutableLiveData<Cliente>()


    fun salvarCliente(cliente : Cliente){
        doAsync {
            dadosCliente.value = cliente
            useCase.execute(cliente)
            sucessoCadastro.value = true
        }
    }

    fun gerarToken(): String{
        return UUID.randomUUID().toString()
    }

    fun getCLiente(): Cliente?{
        return dadosCliente.value
    }
}