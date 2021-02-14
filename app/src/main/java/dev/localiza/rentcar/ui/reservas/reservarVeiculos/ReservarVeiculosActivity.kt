package dev.localiza.rentcar.ui.reservas.reservarVeiculos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.localiza.rentcar.R

class ReservarVeiculosActivity: AppCompatActivity() {

    private lateinit var reservarVeiculosViewModel: ReservarVeiculosViewModel

      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          setContentView(R.layout.activity_reservar_veiculos)

          inicializarView()
      }

    private fun inicializarView() {
    }
}