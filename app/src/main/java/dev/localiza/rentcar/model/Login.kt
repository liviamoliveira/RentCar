package dev.localiza.rentcar.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Login (
        val login: String,
        val senha: String
): Parcelable