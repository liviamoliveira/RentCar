package dev.localiza.rentcar.ui.reservas.escolhaVeiculos

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getColor
import dev.localiza.rentcar.R

class EscolhaVeiculoActivity : AppCompatActivity() {

    private lateinit var escolhaVeiculoViewModel: EscolhaVeiculoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_escolha_veiculo)

        statusBarPadrao()
    }

    private fun statusBarPadrao() {
        window?.decorView?.systemUiVisibility = View.VISIBLE
        window?.statusBarColor = getColor(this, R.color.verde_contraste)
    }

}