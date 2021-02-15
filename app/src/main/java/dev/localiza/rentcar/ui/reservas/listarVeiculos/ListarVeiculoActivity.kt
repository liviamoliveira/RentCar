package dev.localiza.rentcar.ui.reservas.listarVeiculos

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dev.localiza.rentcar.R
import dev.localiza.rentcar.model.*
import dev.localiza.rentcar.ui.reservas.listarVeiculos.adapter.ListarVeiculoAdapter
import kotlinx.android.synthetic.main.activity_listar_veiculo.*
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import dev.localiza.rentcar.ui.reservas.detalharReservas.DetalharReservasActivity

class ListarVeiculoActivity : AppCompatActivity() {

    private val viewModel by viewModel<EscolherVeiculoViewModel>()

    private val listarVeiculoAdapter by lazy { ListarVeiculoAdapter{
        viewModel.selecaoVeiculo(it)
    }}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_veiculo)

        eventosClique()
        observers()
    }

    private fun observers() {
        viewModel.selecaoVeiculo.observe(this, Observer {veiculo ->
            irParaDetalhesReserva(veiculo)
        })
    }

    private fun irParaDetalhesReserva(veiculo: Veiculo?) {
        val intent = Intent(this, DetalharReservasActivity::class.java)
        intent.putExtra(PARAM_MINHAS_RESERVAS, false)
        intent.putExtra(PARAM_VEICULO, veiculo)
        startActivity(intent)
    }

    private fun eventosClique() {
        rvVeiculos.layoutManager = LinearLayoutManager(this)
        rvVeiculos.adapter = listarVeiculoAdapter

        val veiculo = Veiculo(1,"ABC",
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

        val veiculo2 = Veiculo(2,"ABC",
            65.0,
            50,
            2,
            MarcaVeiculo("FIAT"),
            ModeloVeiculo("Uno"),
            2020,
            CategoriaEnum.BASICO,
            CombustivelEnum.GASOLINA,
            "https://www.localiza.com/brasil-site/geral/Frota/NUNS.png"
        )

        val veiculo3 = Veiculo(3,"ABC",
            150.0,
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

        listarVeiculoAdapter.submitList(listaVeiculos)
    }

    override fun onStart() {
        super.onStart()
        statusBarTransparente()
    }

    private fun statusBarTransparente() {
        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window?.statusBarColor = Color.TRANSPARENT
    }

    companion object {
        private const val PARAM_VEICULO = "PARAM_VEICULO"
        private const val PARAM_MINHAS_RESERVAS = "PARAM_MINHAS_RESERVAS"
    }
}