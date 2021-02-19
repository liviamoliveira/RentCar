package dev.localiza.rentcar.data.network

import dev.localiza.rentcar.data.Repository
import dev.localiza.rentcar.data.network.schema.ClienteSchema
import dev.localiza.rentcar.data.schema.LoginSchema
import dev.localiza.rentcar.model.Agencia
import dev.localiza.rentcar.model.Cliente
import dev.localiza.rentcar.model.Login
import dev.localiza.rentcar.network.GeneralNetworkException
import dev.localiza.rentcar.network.RequestManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class RentCarNetworkRepository(private val api: RentCarApi): Repository {

    override suspend fun buscarCliente(id: Int): Cliente {
        return withContext(Dispatchers.IO) {
            val apiResponse = RequestManager.requestFromApi { api.buscarCliente(id) }?.mapTo()
            apiResponse?.let { it } ?: throw GeneralNetworkException(message = "Dados não encontratos.")
        }
    }

    override suspend fun buscarAgencia(): List<Agencia> {
        return withContext(Dispatchers.IO) {

            val apiResponse = RequestManager.requestFromApi { api.buscarAgencia() }
            apiResponse?.let { it } ?: emptyList()
        }
    }

    override suspend fun cadastrarCliente(cliente: Cliente): Any {
        return withContext(Dispatchers.IO) {
            val apiResponse = RequestManager.requestFromApi { api.cadastrarCliente(ClienteSchema(cliente.id,cliente.nome,cliente.login,
                    cliente.senha,cliente.cep,
                    cliente.cpf,
                    cliente.data_nasc,
                    cliente.logradouro,
                    cliente.bairro,
                    cliente.complemento,
                    cliente.numero,
                    cliente.cidade,cliente.uf, cliente.celular, cliente.email)) }
                 apiResponse ?:  throw GeneralNetworkException(message = "Dados não encontratos.")
        }
    }

    override suspend fun login(login: Login): Cliente {
        return withContext(Dispatchers.IO) {
            val apiResponse = RequestManager.requestFromApi { api.login(LoginSchema(login.login, login.senha)) }?.mapTo()
            apiResponse?.let { it } ?: throw GeneralNetworkException(message = "Dados não encontratos.")
        }
    }
}
