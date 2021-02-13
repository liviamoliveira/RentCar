package dev.localiza.rentcar.ui.reservas.consultar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dev.localiza.rentcar.R

class ConsultarReservaFragment : Fragment() {

    private lateinit var consultarReservaViewModel: ConsultarReservaViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        consultarReservaViewModel =
                ViewModelProvider(this).get(ConsultarReservaViewModel::class.java)

        return inflater.inflate(R.layout.fragment_consultar_reserva, container, false)
    }


}