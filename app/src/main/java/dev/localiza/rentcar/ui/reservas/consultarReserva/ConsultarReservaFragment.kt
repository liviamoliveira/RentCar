package dev.localiza.rentcar.ui.reservas.consultarReserva

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.localiza.rentcar.R
import dev.localiza.rentcar.ui.reservas.listarAgenciaReserva.ListarAgenciaActivity
import dev.localiza.rentcar.ui.reservas.listarReservas.ListarReservasFragment
import kotlinx.android.synthetic.main.fragment_consultar_reserva.*
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
        btCriarReserva.setOnClickListener {
            val intent = Intent(requireContext(), ListarAgenciaActivity::class.java)
            startActivity(intent)
        }

        btConsultarReserva.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                    ?.add(R.id.nav_host_fragment, ListarReservasFragment())
                    ?.commit()

        }
    }
}
