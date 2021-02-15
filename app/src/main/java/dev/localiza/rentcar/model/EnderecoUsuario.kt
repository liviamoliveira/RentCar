package dev.localiza.rentcar.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EnderecoUsuario(
    val cep: String,
    val logradouro: String,
    val bairro: String,
    val complemento: String,
    val numero: String,
    val cidade: String,
    val estado: String
): Parcelable
