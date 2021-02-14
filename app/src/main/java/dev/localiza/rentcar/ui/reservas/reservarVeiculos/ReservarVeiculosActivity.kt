package dev.localiza.rentcar.ui.reservas.reservarVeiculos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.localiza.rentcar.R
import dev.localiza.rentcar.ui.reservas.detalharReservas.DetalharReservasViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReservarVeiculosActivity: AppCompatActivity() {

    private val viewModel by viewModel<ReservarVeiculosViewModel>()

      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          setContentView(R.layout.activity_reservar_veiculos)

          inicializarView()
      }

    private fun inicializarView() {
    }
}