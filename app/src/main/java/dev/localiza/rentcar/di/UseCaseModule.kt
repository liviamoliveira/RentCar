package dev.localiza.rentcar.di


import dev.localiza.rentcar.usecase.BuscarAgenciasUseCase
import dev.localiza.rentcar.usecase.BuscarClienteUseCase
import dev.localiza.rentcar.usecase.CadastrarClienteUseCase
import dev.localiza.rentcar.usecase.LogarUseCase
import org.koin.dsl.module

internal val useCaseModule = module {
    factory { BuscarClienteUseCase(repo = get()) }
    factory { CadastrarClienteUseCase(repo = get()) }
    factory { LogarUseCase(repo = get()) }
    factory { BuscarAgenciasUseCase(repo = get()) }
}
