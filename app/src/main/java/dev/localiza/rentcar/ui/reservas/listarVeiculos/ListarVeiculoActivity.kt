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
import dev.localiza.rentcar.ui.reservas.detalharReserva.DetalharReservasActivity
import dev.localiza.rentcar.ui.reservas.selecionarDataHoraReserva.SelecionarDataHoraActivity
import java.util.*

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
        parametrosIniciais()
    }

    private fun observers() {
        viewModel.selecaoVeiculo.observe(this, Observer {veiculo ->
            irParaDetalhesReserva(veiculo)
        })
    }

    private fun eventosClique() {
        rvVeiculos.layoutManager = LinearLayoutManager(this)
        rvVeiculos.adapter = listarVeiculoAdapter
    }

    private fun irParaDetalhesReserva(veiculo: Veiculo?) {
        val intent = Intent(this, DetalharReservasActivity::class.java)
        intent.putExtra(PARAM_MINHAS_RESERVAS, false)
        intent.putExtra(PARAM_VEICULO, veiculo)
        intent.putExtra(PARAM_AGENCIA, viewModel.getAgencia())
        intent.putExtra(PARAM_DATA_RETIRADA, viewModel.getDataHoraRetirada())
        intent.putExtra(PARAM_DATA_DEVOLUCAO, viewModel.getDataHoraDevolucao())

        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        statusBarTransparente()
    }

    private fun statusBarTransparente() {
        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window?.statusBarColor = Color.TRANSPARENT
    }

    private fun parametrosIniciais() {
        val agencia = intent.getParcelableExtra<Agencia>(PARAM_AGENCIA) ?: return
        val dataRetirada = intent.getParcelableExtra<Horario>(PARAM_DATA_RETIRADA) ?: return
        val dataDevolucao = intent.getParcelableExtra<Horario>(PARAM_DATA_DEVOLUCAO) ?: return

        viewModel.setAgencia(agencia)
        viewModel.setDataHoraRetirada(dataRetirada)
        viewModel.setDataHoraDevolucao(dataDevolucao)

        if(agencia.veiculos == null){

            val veiculo = Veiculo(3,"ABC",
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


            val veiculoAgencia = listOf(VeiculoAgencia(1,1,veiculo))

            val agee = Agencia(1,"Codigo","Cristiano Machado",veiculoAgencia)

            listarVeiculoAdapter.submitList(agee.veiculos)

        }
        else{
            listarVeiculoAdapter.submitList(agencia.veiculos)
        }
    }

    companion object {
        private const val PARAM_VEICULO = "PARAM_VEICULO"
        private const val PARAM_AGENCIA = "PARAM_AGENCIA"
        private const val PARAM_MINHAS_RESERVAS = "PARAM_MINHAS_RESERVAS"
        private const val PARAM_DATA_RETIRADA = "PARAM_DATA_RETIRADA"
        private const val PARAM_DATA_DEVOLUCAO = "PARAM_DATA_DEVOLUCAO"
    }
}