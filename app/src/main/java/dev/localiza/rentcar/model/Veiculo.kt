package dev.localiza.rentcar.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Veiculo (
        val id: Int,
        val placa: String,
        val valorHora: Double,
        val capacidadeTanqueCombustivel: Int,
        val capacidadePortaMalas: Int,
        val marca: MarcaVeiculo,
        val modelo: ModeloVeiculo,
        val ano: Int,
        val categoria: CategoriaEnum,
        val combustivel: CombustivelEnum,
        val urlVeiculo: String
): Parcelable

enum class CategoriaEnum {
        BASICO,
        COMPLETO,
        LUXO
}

enum class CombustivelEnum {
        ALCOOL,
        GASOLINA,
        DIESEL
}