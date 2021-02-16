package dev.localiza.rentcar.ui.reservas.listarAgenciaReserva

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dev.localiza.rentcar.R
import dev.localiza.rentcar.model.*
import dev.localiza.rentcar.ui.reservas.listarAgenciaReserva.adapter.ListarAgenciaAdapter
import dev.localiza.rentcar.ui.reservas.selecionarDataHoraReserva.SelecionarDataHoraActivity
import kotlinx.android.synthetic.main.activity_listar_agencias.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListarAgenciaActivity: AppCompatActivity() {

    private val viewModel by viewModel<ListarAgenciaViewModel>()

    private val agenciaAdapter by lazy { ListarAgenciaAdapter{
         viewModel.selecaoAgencia(it)
     }}

      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          setContentView(R.layout.activity_listar_agencias)

          eventosClique()
          observers()
      }

    override fun onStart() {
        super.onStart()
        statusBarTransparente()
    }

    private fun observers() {
        viewModel.selecaoAgencia.observe(this, androidx.lifecycle.Observer {
            val intent = Intent(this, SelecionarDataHoraActivity::class.java)
            intent.putExtra(PARAM_AGENCIA, it)
            startActivity(intent)
        })
    }

    private fun eventosClique() {
        rvAgencia.layoutManager = LinearLayoutManager(this)
        rvAgencia.adapter = agenciaAdapter

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

        val veiculoAgencia = VeiculoAgencia(1,1,veiculo)
        val veiculoAgencia2 = VeiculoAgencia(1,1,veiculo2)

        val agencia = Agencia(1,"ABC", "Agência Cristiano Machado", listOf(veiculoAgencia, veiculoAgencia2))
        val agencia2 = Agencia(2,"ABCD", "Agência Lourdes", listOf(veiculoAgencia))

        val listaAgencia = listOf(agencia,agencia2)

        agenciaAdapter.submitList(listaAgencia)
    }

    private fun statusBarTransparente() {
        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window?.statusBarColor = Color.TRANSPARENT
    }

    companion object {
        private const val PARAM_AGENCIA = "PARAM_AGENCIA"
    }

}