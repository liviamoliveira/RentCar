package dev.localiza.rentcar.data.schema

import com.google.gson.annotations.SerializedName
import dev.localiza.rentcar.model.Agencia

internal data class AgenciaListDto(
        @SerializedName("count")
        val count: Int,
        @SerializedName("results")
        val results: List<Agencia>
)