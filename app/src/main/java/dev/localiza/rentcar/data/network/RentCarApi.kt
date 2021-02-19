package dev.localiza.rentcar.data.network

import dev.localiza.rentcar.data.network.schema.ClienteSchema
import dev.localiza.rentcar.data.schema.LoginSchema
import dev.localiza.rentcar.model.Agencia
import retrofit2.Response
import retrofit2.http.*

internal interface RentCarApi {
    @GET("/api/Cliente/{id}")
    suspend fun buscarCliente(@Path("id") id: Int) : Response<ClienteSchema>

    @GET("/api/Agencia")
    suspend fun buscarAgencia(): Response<List<Agencia>>

    @POST("/api/Cliente")
    suspend fun cadastrarCliente(@Body cliente: ClienteSchema) : Response<Any>

    @POST("/api/Login")
    suspend fun login(@Body login: LoginSchema) : Response<ClienteSchema>
}
