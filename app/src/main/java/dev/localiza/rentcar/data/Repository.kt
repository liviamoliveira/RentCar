package dev.localiza.rentcar.data

import dev.localiza.rentcar.model.Agencia
import dev.localiza.rentcar.model.Cliente
import dev.localiza.rentcar.model.Login


internal interface Repository {

    suspend fun buscarCliente(id: Int) : Cliente
    suspend fun buscarAgencia() : List<Agencia>
    suspend fun cadastrarCliente(cliente: Cliente): Any
    suspend fun login(login: Login): Cliente
}
