package dev.localiza.rentcar.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModeloVeiculo (
    val nome: String
): Parcelable
