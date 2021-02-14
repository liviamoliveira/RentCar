package dev.localiza.rentcar.ui.reservas.listarReservas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dev.localiza.rentcar.R

class ListarReservasFragment : Fragment() {

    private lateinit var listarReservasViewModel: ListarReservasViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        listarReservasViewModel =
                ViewModelProvider(this).get(ListarReservasViewModel::class.java)

        inicializarView()

        return inflater.inflate(R.layout.fragment_listar_reservas, container, false)
    }

    private fun inicializarView() {

    }
}