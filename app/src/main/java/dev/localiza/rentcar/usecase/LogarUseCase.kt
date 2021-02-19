package dev.localiza.rentcar.usecase

import dev.localiza.rentcar.data.Repository
import dev.localiza.rentcar.model.Cliente
import dev.localiza.rentcar.model.Login
import dev.localiza.rentcar.usecase.base.UseCase


internal class LogarUseCase(private val repo: Repository) : UseCase<Login, Cliente> {
    override suspend fun execute(login: Login): Cliente {
        return repo.login(login)
    }
}
