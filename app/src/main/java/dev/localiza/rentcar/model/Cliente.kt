package dev.localiza.rentcar.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Cliente (
        val id: Int,
        val nome: String?,
        val cpf: String?,
        val data_nasc: Date?,
        val cep: String?,
        val logradouro: String?,
        val numero: Int,
        val uf: String?,
        val complemento: String?,
        val bairro: String?,
        val cidade: String?,
        val login: String?,
        val senha: String?,
        val celular: String?,
        val email: String?
): Parcelable
