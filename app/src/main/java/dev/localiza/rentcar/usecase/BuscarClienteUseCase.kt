package dev.localiza.rentcar.usecase

import dev.localiza.rentcar.data.Repository
import dev.localiza.rentcar.model.Cliente
import dev.localiza.rentcar.usecase.base.UseCase

internal class BuscarClienteUseCase(private val repo: Repository) : UseCase<Int, Cliente> {
    override suspend fun execute(id: Int): Cliente{
        return repo.buscarCliente(id)
    }
}
