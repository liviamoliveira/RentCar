package dev.localiza.rentcar.model

data class Veiculo (
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
)

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