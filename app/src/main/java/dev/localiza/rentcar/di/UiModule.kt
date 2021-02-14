package dev.localiza.rentcar.di

import dev.localiza.rentcar.ui.cadastro.CadastroViewModel
import dev.localiza.rentcar.ui.home.HomeViewModel
import dev.localiza.rentcar.ui.reservas.consultar.ConsultarReservaViewModel
import dev.localiza.rentcar.ui.reservas.detalharReservas.DetalharReservasViewModel
import dev.localiza.rentcar.ui.reservas.listarReservas.ListarReservasViewModel
import dev.localiza.rentcar.ui.reservas.listarVeiculos.EscolherVeiculoViewModel
import dev.localiza.rentcar.ui.reservas.reservarVeiculos.ReservarVeiculosViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { CadastroViewModel() }
    viewModel { HomeViewModel() }
    viewModel { ConsultarReservaViewModel() }
    viewModel { DetalharReservasViewModel() }
    viewModel { ListarReservasViewModel() }
    viewModel { ReservarVeiculosViewModel() }
    viewModel { EscolherVeiculoViewModel() }
}