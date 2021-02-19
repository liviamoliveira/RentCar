package dev.localiza.rentcar.ui.login

import dev.localiza.rentcar.base.BaseViewModel
import dev.localiza.rentcar.base.extension.SingleLiveEvent
import dev.localiza.rentcar.model.Cliente
import dev.localiza.rentcar.model.Login
import dev.localiza.rentcar.usecase.LogarUseCase
import java.util.*

internal class LoginViewModel(private val useCase: LogarUseCase): BaseViewModel() {

    val loginSucesso = SingleLiveEvent<Cliente>()

    fun logar(login: Login) {
        doAsync {
            val response = useCase.execute(login)
            loginSucesso.value = response
        }
    }

    fun gerarToken(): String{
        return UUID.randomUUID().toString()
    }
}