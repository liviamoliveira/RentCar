package dev.localiza.rentcar.data.schema

import com.google.gson.annotations.SerializedName
import dev.localiza.rentcar.model.*
import dev.localiza.rentcar.network.MapTo
import java.util.*

internal data class ReservaSchema (
        @SerializedName("id")
        val id: Int,
        @SerializedName("veiculoId")
        val veiculoId: Int,
        @SerializedName("veiculo")
        val veiculo: Veiculo?,
        @SerializedName("totalHorasLocacao")
        val totalHorasLocacao: Double,
        @SerializedName("valorTotal")
        val valorTotal: Double,
        @SerializedName("operadorId")
        val operadorId: Int,
        @SerializedName("operador")
        val operador: Usuario?,
        @SerializedName("clienteId")
        val clienteId: Int,
        @SerializedName("cliente")
        val cliente: Usuario?,
        @SerializedName("dataRetirada")
        val dataRetirada: Date?,
        @SerializedName("localRetiradaId")
        val localRetiradaId: Int,
        @SerializedName("localRetirada")
        val localRetirada: Agencia?,
        @SerializedName("dataDevolucao")
        val dataDevolucao: Date?,
        @SerializedName("localDevolucaoId")
        val localDevolucaoId: Int,
        @SerializedName("localDevolucao")
        val localDevolucao: Agencia?,
        @SerializedName("checkListDevolucaoId")
        val checkListDevolucaoId: Int,
        @SerializedName("checkListRetiradaId")
        val checkListRetiradaId: Int
): MapTo<Reserva> {

    override fun mapTo() = Reserva(
            id = id,
            veiculoId = veiculoId,
            veiculo = veiculo,
            totalHorasLocacao = totalHorasLocacao,
            valorTotal = valorTotal,
            operadorId = operadorId,
            operador = operador,
            clienteId = clienteId,
            cliente = cliente,
            dataRetirada = dataRetirada,
            localRetiradaId = localRetiradaId,
            localRetirada = localRetirada,
            dataDevolucao = dataDevolucao,
            localDevolucaoId = localDevolucaoId,
            localDevolucao = localDevolucao,
            checkListRetiradaId = checkListRetiradaId,
            checkListDevolucaoId = checkListDevolucaoId
    )
}
