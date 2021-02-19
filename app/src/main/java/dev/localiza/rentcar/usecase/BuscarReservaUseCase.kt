package dev.localiza.rentcar.usecase

import dev.localiza.rentcar.data.Repository
import dev.localiza.rentcar.model.Reserva
import dev.localiza.rentcar.usecase.base.UseCase


internal class BuscarReservaUseCase(private val repo: Repository) : UseCase<String, List<Reserva>> {
    override suspend fun execute(cpf: String): List<Reserva> {
        return repo.buscarReservaPorCPF(cpf)
    }
}
