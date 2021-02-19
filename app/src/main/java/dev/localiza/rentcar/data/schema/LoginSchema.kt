package dev.localiza.rentcar.data.schema

import com.google.gson.annotations.SerializedName
import dev.localiza.rentcar.model.Login
import dev.localiza.rentcar.network.MapTo


internal data class LoginSchema (
        @SerializedName("login")
        val login: String,
        @SerializedName("senha")
        val senha: String
): MapTo<Login> {

    override fun mapTo() = Login(
            login=login,
            senha = senha
    )
}
