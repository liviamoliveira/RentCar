package dev.localiza.rentcar.ui.reservas.dadosReserva

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import dev.localiza.rentcar.R
import dev.localiza.rentcar.ui.reservas.confirmarReserva.ConfirmarReservaActivity
import kotlinx.android.synthetic.main.activity_dados_pessoais_reserva.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class InformarDadosReservaActivity: AppCompatActivity() {

    private val viewModel by viewModel<InformarDadosReservaViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dados_pessoais_reserva)

        eventosClique()
    }

    override fun onStart() {
        super.onStart()
        statusBarTransparente()
    }

    private fun eventosClique() {
        btConfirmarReserva.setOnClickListener {
            val intent = Intent(this, ConfirmarReservaActivity::class.java)
            startActivity(intent)
        }
    }

    private fun statusBarTransparente() {
        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window?.statusBarColor = Color.TRANSPARENT
    }

}