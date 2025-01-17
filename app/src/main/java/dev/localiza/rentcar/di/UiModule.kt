package dev.localiza.rentcar.di

import dev.localiza.rentcar.ui.cadastro.CadastroViewModel
import dev.localiza.rentcar.ui.home.HomeViewModel
import dev.localiza.rentcar.ui.login.LoginViewModel
import dev.localiza.rentcar.ui.perfil.PerfilViewModel
import dev.localiza.rentcar.ui.reservas.confirmarReserva.ConfirmarReservaViewModel
import dev.localiza.rentcar.ui.reservas.consultarReserva.ConsultarReservaViewModel
import dev.localiza.rentcar.ui.reservas.detalharReserva.DetalharReservasViewModel
import dev.localiza.rentcar.ui.reservas.dadosReserva.InformarDadosReservaViewModel
import dev.localiza.rentcar.ui.reservas.listarReservas.ListarReservasViewModel
import dev.localiza.rentcar.ui.reservas.listarVeiculos.EscolherVeiculoViewModel
import dev.localiza.rentcar.ui.reservas.listarAgenciaReserva.ListarAgenciaViewModel
import dev.localiza.rentcar.ui.reservas.selecionarDataHoraReserva.SelecionarDataHoraViewModel
import okhttp3.internal.userAgent
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { CadastroViewModel(useCase = get()) }
    viewModel { HomeViewModel() }
    viewModel { ConsultarReservaViewModel(useCase = get()) }
    viewModel { DetalharReservasViewModel(useCase = get()) }
    viewModel { ListarReservasViewModel(useCase = get()) }
    viewModel { ListarAgenciaViewModel(useCase = get()) }
    viewModel { EscolherVeiculoViewModel() }
    viewModel { InformarDadosReservaViewModel(useCase = get()) }
    viewModel { ConfirmarReservaViewModel() }
    viewModel { SelecionarDataHoraViewModel() }
    viewModel { LoginViewModel(useCase = get()) }
    viewModel { PerfilViewModel() }
}
