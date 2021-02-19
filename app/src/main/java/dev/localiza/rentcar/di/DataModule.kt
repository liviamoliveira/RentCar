package dev.localiza.rentcar.di

import dev.localiza.rentcar.data.Repository
import dev.localiza.rentcar.data.network.RentCarApi
import dev.localiza.rentcar.data.network.RentCarNetworkRepository
import dev.localiza.rentcar.network.ApiClientBuilder
import org.koin.dsl.bind
import org.koin.dsl.module

internal val dataModule = module {
    single { ApiClientBuilder.createServiceApi(RentCarApi::class.java, baseUrl = "https://g6rentcar.azurewebsites.net/") }
    factory { RentCarNetworkRepository(api = get()) } bind Repository::class
}
