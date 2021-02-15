package dev.localiza.rentcar.ui.reservas.detalharReservas

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import dev.localiza.rentcar.R
import dev.localiza.rentcar.model.CategoriaEnum
import dev.localiza.rentcar.model.Veiculo
import dev.localiza.rentcar.ui.reservas.informarDadosCadastro.InformarDadosReservaActivity
import kotlinx.android.synthetic.main.activity_detalhar_veiculo.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetalharReservasActivity : AppCompatActivity() {

    private val viewModel by viewModel<DetalharReservasViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhar_veiculo)

        parametrosIniciais()
        eventosClique()
        observers()
    }

    override fun onStart() {
        super.onStart()
        statusBarTransparente()
    }


    private fun eventosClique() {
        btnConfirmarReserva.setOnClickListener {
            val intent = Intent(this, InformarDadosReservaActivity::class.java)
            startActivity(intent)
        }
        btnMinhasReserva.setOnClickListener {
         finish()
        }
    }

    private fun observers() {
        viewModel.exibirVeiculo.observe(this, Observer {veiculo ->
            tvMarcaVeiculo.text = veiculo.marca.nome
            tvModeloVeiculo.text = veiculo.modelo.nome
            tvPrecoTotal.text = "R$ " + "%.2f".format(veiculo.valorHora).replace(".",",")
            tvCategoriaVeiculo.text = tipoCategoriaDescricao(veiculo.categoria)

            Glide.with(this).load(veiculo.urlVeiculo)
                .placeholder(R.drawable.ic_logo)
                .into(ivCarro)
        })
        viewModel.exibirBotao.observe(this, Observer {exibir ->
           btnMinhasReserva.isVisible = exibir
           btnConfirmarReserva.isVisible = !exibir
        })
    }

    private fun statusBarTransparente() {
        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window?.statusBarColor = Color.TRANSPARENT
    }

    private fun tipoCategoriaDescricao(categoria: CategoriaEnum): String {
        return when (categoria) {
            CategoriaEnum.BASICO -> "Básico"
            CategoriaEnum.COMPLETO -> "Completo"
            else -> "Luxo"
        }
    }

    fun parametrosIniciais() {
        val veiculo = intent.getParcelableExtra<Veiculo>(PARAM_VEICULO) ?: return
        val exibirBotao = intent.getBooleanExtra(PARAM_MINHAS_RESERVAS,false)
        viewModel.init(veiculo,exibirBotao)
    }

    companion object {
        private const val PARAM_VEICULO = "PARAM_VEICULO"
        private const val PARAM_MINHAS_RESERVAS = "PARAM_MINHAS_RESERVAS"
    }
}