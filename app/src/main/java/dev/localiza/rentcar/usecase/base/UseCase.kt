package dev.localiza.rentcar.usecase.base

interface UseCase<Param, Return> {
    suspend fun execute(param:Param) : Return
}