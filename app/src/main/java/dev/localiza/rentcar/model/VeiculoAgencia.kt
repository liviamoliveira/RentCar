package dev.localiza.rentcar.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VeiculoAgencia (
    val agenciaId: Int,
    val veiculId: Int,
    val veiculo: Veiculo
): Parcelable