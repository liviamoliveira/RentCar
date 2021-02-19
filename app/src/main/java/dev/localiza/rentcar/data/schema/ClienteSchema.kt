package dev.localiza.rentcar.data.network.schema

import com.google.gson.annotations.SerializedName
import dev.localiza.rentcar.model.Cliente
import dev.localiza.rentcar.network.MapTo
import java.util.*

internal data class ClienteSchema (
        @SerializedName("id")
        val id: Int,
        @SerializedName("nome")
        val nome: String?,
        @SerializedName("login")
        val login: String?,
        @SerializedName("senha")
        val senha: String?,
        @SerializedName("cep")
        val cep: String?,
        @SerializedName("cpf")
        val cpf: String?,
        @SerializedName("data_nasc")
        val data_nasc: Date?,
        @SerializedName("logradouro")
        val logradouro: String?,
        @SerializedName("bairro")
        val bairro: String?,
        @SerializedName("complemento")
        val complemento: String?,
        @SerializedName("numero")
        val numero: Int,
        @SerializedName("cidade")
        val cidade: String?,
        @SerializedName("uf")
        val uf: String?,
        @SerializedName("celular")
        val celular: String?,
        @SerializedName("email")
        val email: String?
): MapTo<Cliente> {

        override fun mapTo() = Cliente(
                id = id,
                nome=nome,
                login=login,
                senha = senha,
                cep = cep,
                cpf = cpf,
                data_nasc= data_nasc,
                logradouro = logradouro,
                bairro = bairro,
                complemento = complemento,
                numero = numero,
                cidade = cidade,
                uf = uf,
                celular = celular,
                email = email
        )
}