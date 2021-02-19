package dev.localiza.rentcar.ui.reservas.confirmarReserva

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import dev.localiza.rentcar.MainActivity
import dev.localiza.rentcar.R
import dev.localiza.rentcar.model.Cliente
import dev.localiza.rentcar.ui.reservas.dadosReserva.InformarDadosReservaActivity
import kotlinx.android.synthetic.main.activity_confirmar_reserva.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConfirmarReservaActivity : AppCompatActivity() {

    private val viewModel by viewModel<ConfirmarReservaViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmar_reserva)

        eventosClique()
        parametrosIniciais()
    }

    override fun onStart() {
        super.onStart()
        statusBarTransparente()
    }

    private fun eventosClique() {
        btOk.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun statusBarTransparente() {
        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window?.statusBarColor = Color.TRANSPARENT
    }

    private fun parametrosIniciais() {
       val idReserva =  intent.getIntExtra(PARAM_ID_RESERVA, 0)
        tvNumeroReservaEx.text = idReserva.toString()
    }

    companion object {
        private const val PARAM_ID_RESERVA = "PARAM_ID_RESERVA"
    }
}