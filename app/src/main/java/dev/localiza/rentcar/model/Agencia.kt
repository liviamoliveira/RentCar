package dev.localiza.rentcar.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Agencia(
    val id: Int,
    val codigo: String,
    val nome: String,
    val veiculos: List<VeiculoAgencia>
): Parcelable