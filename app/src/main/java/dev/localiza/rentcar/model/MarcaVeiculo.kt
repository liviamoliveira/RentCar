package dev.localiza.rentcar.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MarcaVeiculo (
    val nome: String
): Parcelable