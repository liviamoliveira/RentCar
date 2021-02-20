package dev.localiza.rentcar.base

interface MapToModel<Model> {
    fun toModel(): Model
}

inline fun <Model> Iterable<MapToModel<Model>>.toModel() = this.map { it.toModel() }
