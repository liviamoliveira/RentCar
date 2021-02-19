package dev.localiza.rentcar.usecase

import dev.localiza.rentcar.data.Repository
import dev.localiza.rentcar.model.Reserva
import dev.localiza.rentcar.usecase.base.UseCase

internal class ReservarUseCase(private val repo: Repository) : UseCase<Reserva, Reserva> {
    override suspend fun execute(reserva: Reserva): Reserva {
        return repo.reservar(reserva)
    }
}