package dev.localiza.rentcar.network

interface MapTo<T> {
    fun mapTo(): T
}

fun <T> Iterable<MapTo<T>>.mapTo() = this.map { it.mapTo() }