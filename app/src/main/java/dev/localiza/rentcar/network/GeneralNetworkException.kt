package dev.localiza.rentcar.network

import java.lang.Exception

open class GeneralNetworkException(message: String? = null, cause: Throwable? = null) : Exception(message ?: cause?.message, cause)