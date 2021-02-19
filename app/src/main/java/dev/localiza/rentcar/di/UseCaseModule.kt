package dev.localiza.rentcar.di


import dev.localiza.rentcar.usecase.BuscarAgenciasUseCase
import dev.localiza.rentcar.usecase.BuscarClienteUseCase
import dev.localiza.rentcar.usecase.BuscarReservaUseCase
import dev.localiza.rentcar.usecase.CadastrarClienteUseCase
import dev.localiza.rentcar.usecase.LogarUseCase
import dev.localiza.rentcar.usecase.ReservarUseCase
import org.koin.dsl.module

internal val useCaseModule = module {
    factory { BuscarClienteUseCase(repo = get()) }
    factory { CadastrarClienteUseCase(repo = get()) }
    factory { LogarUseCase(repo = get()) }
    factory { BuscarAgenciasUseCase(repo = get()) }
    factory { ReservarUseCase(repo = get()) }
    factory { BuscarReservaUseCase(repo = get()) }
}
