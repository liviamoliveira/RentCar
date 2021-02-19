package dev.localiza.rentcar.data.schema

import com.google.gson.annotations.SerializedName
import dev.localiza.rentcar.model.Agencia
import dev.localiza.rentcar.model.VeiculoAgencia
import dev.localiza.rentcar.network.MapTo

internal data class AgenciaSchema (
        @SerializedName("id")
        val id: Int,
        @SerializedName("codigo")
        val codigo: String,
        @SerializedName("nome")
        val nome: String,
        @SerializedName("veiculos")
        val veiculos: List<VeiculoAgencia>?
): MapTo<Agencia> {

    override fun mapTo() = Agencia(
            id=id,
            codigo = codigo,
            nome = nome,
            veiculos = veiculos
    )
}
