package dev.localiza.rentcar.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
internal data class Horario(
        val exibicao: String,
        val dataHora: Date
) : Parcelable {
}
