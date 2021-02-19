package dev.localiza.rentcar.data.network

import dev.localiza.rentcar.data.Repository
import dev.localiza.rentcar.data.network.schema.ClienteSchema
import dev.localiza.rentcar.data.schema.LoginSchema
import dev.localiza.rentcar.data.schema.ReservaSchema
import dev.localiza.rentcar.model.Agencia
import dev.localiza.rentcar.model.Cliente
import dev.localiza.rentcar.model.Login
import dev.localiza.rentcar.model.Reserva
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

    override suspend fun reservar(reserva: Reserva): Reserva {
        return withContext(Dispatchers.IO) {
            val apiResponse = RequestManager.requestFromApi { api.reservar(ReservaSchema(reserva.id,
                    reserva.veiculoId,
                    reserva.veiculo,
                    reserva.totalHorasLocacao,
                    reserva.valorTotal,
                    reserva.operadorId,
                    reserva.operador,
                    reserva.clienteId,
                    reserva.cliente,
                    reserva.dataRetirada,
                    reserva.localRetiradaId,
                    reserva.localRetirada,
                    reserva.dataDevolucao,
                    reserva.localDevolucaoId,
                    reserva.localDevolucao, reserva.checkListDevolucaoId, reserva.checkListDevolucaoId)) }?.mapTo()
            apiResponse ?:  throw GeneralNetworkException(message = "Dados não encontratos.")
        }
    }

    override suspend fun buscarReservaPorCPF(cpf: String): List<Reserva> {
        return withContext(Dispatchers.IO) {

            val apiResponse = RequestManager.requestFromApi { api.buscarReservaPorCPF(cpf) }
            apiResponse?.let { it } ?: emptyList()
        }
    }

    override suspend fun login(login: Login): Cliente {
        return withContext(Dispatchers.IO) {
            val apiResponse = RequestManager.requestFromApi { api.login(LoginSchema(login.login, login.senha)) }?.mapTo()
            apiResponse?.let { it } ?: throw GeneralNetworkException(message = "Dados não encontratos.")
        }
    }
}
