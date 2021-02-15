package dev.localiza.rentcar.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class RegistroUsuario(
 val documento: String,
 val nome: String,
 val dataNascimento: Date,
 val tipo: TipoUsuarioEnum,
 val enderecoId: Int,
 val endereco: EnderecoUsuario
): Parcelable

enum class TipoUsuarioEnum {
    CLIENTE,
    OPERADOR
}