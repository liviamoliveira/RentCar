package dev.localiza.rentcar.usecase

import dev.localiza.rentcar.data.Repository
import dev.localiza.rentcar.model.Cliente
import dev.localiza.rentcar.usecase.base.UseCase

internal class CadastrarClienteUseCase(private val repo: Repository) : UseCase<Cliente, Any> {
    override suspend fun execute(cliente: Cliente): Any {
        return repo.cadastrarCliente(cliente)
    }
}
