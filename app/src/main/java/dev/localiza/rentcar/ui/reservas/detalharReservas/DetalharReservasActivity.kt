package dev.localiza.rentcar.ui.reservas.detalharReservas

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import dev.localiza.rentcar.R

class DetalharReservasActivity : AppCompatActivity() {

    private lateinit var detalharReservasViewModel: DetalharReservasViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhar_veiculo)
        inicializarView()
    }

    private fun inicializarView() {
    }


    override fun onStart() {
        super.onStart()
        statusBarTransparente()
    }

    private fun statusBarTransparente() {
        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window?.statusBarColor = Color.TRANSPARENT
    }

}