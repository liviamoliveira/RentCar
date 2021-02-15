package dev.localiza.rentcar.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Reserva (
    val id: Int,
    val veiculoId: Int,
    val veiculo: Veiculo,
    val totalHorasLocacao: Double,
    val valorTotal: Double,
    val operadorId: Int,
    val operador: Usuario,
    val clienteId: Int,
    val client: Usuario,
    val dataRetirada: Date,
    val localRetiradaId: Int,
    val localRetirada: Agencia,
    val dataDevolucao: Date,
    val localDevolucaoId: Int,
    val localDevolucao: Agencia
): Parcelable