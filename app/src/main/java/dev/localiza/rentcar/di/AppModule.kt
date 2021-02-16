package dev.localiza.rentcar.di

import org.koin.core.module.Module

object AppModule {
    fun getModules(): List<Module> = listOf(
        uiModule,
        dataModule,
        useCaseModule
    )
}