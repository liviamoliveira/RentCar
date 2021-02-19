package dev.localiza.rentcar.ui.cadastro

import dev.localiza.rentcar.base.BaseViewModel
import dev.localiza.rentcar.base.extension.SingleLiveEvent
import dev.localiza.rentcar.model.Cliente
import dev.localiza.rentcar.usecase.CadastrarClienteUseCase

internal class CadastroViewModel(private val useCase: CadastrarClienteUseCase) : BaseViewModel() {

    val sucessoCadastro = SingleLiveEvent<Boolean>()

    fun salvarCliente(cliente : Cliente){
        doAsync {
           useCase.execute(cliente)
            sucessoCadastro.value = true
        }
    }
}