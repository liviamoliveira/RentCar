package dev.localiza.rentcar.ui.reservas.consultar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.localiza.rentcar.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConsultarReservaFragment : Fragment() {

    private val viewModel by viewModel<ConsultarReservaViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       return inflater.inflate(R.layout.fragment_consultar_reserva, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        eventosClique()
    }

    private fun eventosClique() {

    }
}