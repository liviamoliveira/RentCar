package dev.localiza.rentcar.usecase

import dev.localiza.rentcar.data.Repository
import dev.localiza.rentcar.model.Agencia
import dev.localiza.rentcar.usecase.base.UseCase

internal class BuscarAgenciasUseCase(private val repo: Repository) : UseCase<Unit, List<Agencia>> {
    override suspend fun execute(param: Unit): List<Agencia> {
        return repo.buscarAgencia()
    }
}
