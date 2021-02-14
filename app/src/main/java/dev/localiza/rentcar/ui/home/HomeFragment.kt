package dev.localiza.rentcar.ui.home

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dev.localiza.rentcar.R
import dev.localiza.rentcar.ui.reservas.detalharReservas.DetalharReservasActivity
import dev.localiza.rentcar.ui.reservas.listarVeiculos.ListarVeiculoActivity
import dev.localiza.rentcar.ui.reservas.reservarVeiculos.ReservarVeiculosActivity
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        inicializarView()
    }

    private fun inicializarView() {
        btReservar.setOnClickListener {
            val intent = Intent(requireContext(), DetalharReservasActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        statusBarTransparente()
    }

    private fun statusBarTransparente() {
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        activity?.window?.statusBarColor = Color.TRANSPARENT
    }
}