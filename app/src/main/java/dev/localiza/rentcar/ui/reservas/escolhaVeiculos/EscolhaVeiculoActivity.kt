package dev.localiza.rentcar.ui.reservas.escolhaVeiculos

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.LinearLayoutManager
import dev.localiza.rentcar.R
import dev.localiza.rentcar.model.*
import dev.localiza.rentcar.ui.reservas.escolhaVeiculos.adapter.EscolhaVeiculoAdapter
import kotlinx.android.synthetic.main.activity_escolha_veiculo.*

class EscolhaVeiculoActivity : AppCompatActivity() {

    private lateinit var escolhaVeiculoViewModel: EscolhaVeiculoViewModel

    private val escolhaVeiculoAdapter by lazy { EscolhaVeiculoAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_escolha_veiculo)

        setupView()
    }

    private fun setupView() {
        rvVeiculos.layoutManager = LinearLayoutManager(this)
        rvVeiculos.adapter = escolhaVeiculoAdapter

        val veiculo = Veiculo("ABC",
            45.0,
            50,
            2,
            MarcaVeiculo("Chevrolet"),
            ModeloVeiculo("Onix"),
            2020,
            CategoriaEnum.BASICO,
            CombustivelEnum.GASOLINA,
            "https://revistacarro.com.br/wp-content/uploads/2018/05/chevrolet_onix_joy1.png"
        )

        val veiculo2 = Veiculo("ABC",
            45.0,
            50,
            2,
            MarcaVeiculo("FIAT"),
            ModeloVeiculo("Uno"),
            2020,
            CategoriaEnum.BASICO,
            CombustivelEnum.GASOLINA,
            "https://www.localiza.com/brasil-site/geral/Frota/NUNS.png"
        )

        val veiculo3 = Veiculo("ABC",
            45.0,
            50,
            2,
            MarcaVeiculo("MERCEDES-BENZ"),
            ModeloVeiculo("Mercedes"),
            2020,
            CategoriaEnum.LUXO,
            CombustivelEnum.GASOLINA,
            "https://www.localiza.com/brasil-site/geral/Frota/MCEX.png"
        )

        val listaVeiculos = listOf(veiculo, veiculo2, veiculo3)

        escolhaVeiculoAdapter.submitList(listaVeiculos)
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