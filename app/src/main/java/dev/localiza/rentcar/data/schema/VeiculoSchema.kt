package dev.localiza.rentcar.data.schema

import com.google.gson.annotations.SerializedName
import dev.localiza.rentcar.base.MapToModel
import dev.localiza.rentcar.model.*
import dev.localiza.rentcar.network.MapTo

internal data class VeiculoSchema (
        @SerializedName("id")
        val id: Int,
        @SerializedName("placa")
        val placa: String,
        @SerializedName("valorHora")
        val valorHora: Double,
        @SerializedName("capacidadeTanqueCombustivel")
        val capacidadeTanqueCombustivel: Int,
        @SerializedName("capacidadePortaMalas")
        val capacidadePortaMalas: Int,
        @SerializedName("marca")
        val marca: MarcaVeiculo,
        @SerializedName("modelo")
        val modelo: ModeloVeiculo,
        @SerializedName("ano")
        val ano: Int,
        @SerializedName("categoria")
        val categoria: CategoriaEnumSchema,
        @SerializedName("combustivel")
        val combustivel: CombustivelEnumSchema,
        @SerializedName("urlVeiculo")
        val urlVeiculo: String
): MapTo<Veiculo> {

    override fun mapTo() = Veiculo(
           id = id,
            placa = placa,
            valorHora= valorHora,
            capacidadeTanqueCombustivel = capacidadeTanqueCombustivel,
            capacidadePortaMalas = capacidadePortaMalas,
            marca = marca,
            modelo = modelo,
            ano = ano,
            categoria= categoria.toModel(),
            combustivel = combustivel.toModel(),
            urlVeiculo = urlVeiculo
    )
}

enum class CategoriaEnumSchema : MapToModel<CategoriaEnum> {
    @SerializedName("1")
    BASICO,
    @SerializedName("2")
    COMPLETO,
    @SerializedName("3")
    LUXO;

    override fun toModel() = when(this) {
        BASICO -> CategoriaEnum.BASICO
        COMPLETO -> CategoriaEnum.COMPLETO
        LUXO -> CategoriaEnum.LUXO
    }
}

enum class CombustivelEnumSchema : MapToModel<CombustivelEnum> {
    @SerializedName("1")
    ALCOOL,
    @SerializedName("2")
    GASOLINA,
    @SerializedName("3")
    DIESEL;
    override fun toModel() = when(this) {
        ALCOOL -> CombustivelEnum.ALCOOL
        GASOLINA -> CombustivelEnum.GASOLINA
        DIESEL -> CombustivelEnum.DIESEL
    }
}
