package dev.localiza.rentcar.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Usuario (
    val login: String,
    val senha: String,
    val registroId: Int,
    val registro : RegistroUsuario
): Parcelable