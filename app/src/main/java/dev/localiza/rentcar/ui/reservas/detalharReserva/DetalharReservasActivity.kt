package dev.localiza.rentcar.ui.reservas.detalharReserva

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import dev.localiza.rentcar.R
import dev.localiza.rentcar.model.Agencia
import dev.localiza.rentcar.model.CategoriaEnum
import dev.localiza.rentcar.model.Horario
import dev.localiza.rentcar.model.Veiculo
import dev.localiza.rentcar.ui.reservas.dadosReserva.InformarDadosReservaActivity
import kotlinx.android.synthetic.main.activity_detalhar_veiculo.*
import kotlinx.android.synthetic.main.activity_detalhar_veiculo.tvDetalhesDevolucao
import kotlinx.android.synthetic.main.activity_detalhar_veiculo.tvDetalhesRetirada
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

        viewModel.exibirDadosLocalAgencia.observe(this, Observer {
            tvDetalhesRetirada.text = String.format(getString(R.string.tv_agenciaRetirada), it.nome)
            tvDetalhesDevolucao.text = String.format(getString(R.string.tv_agenciaDevolucao), it.nome)
        })

        viewModel.dataHoraRetiradaTexto.observe(this, Observer {
            tvDetalhesRetirada.text = it
        })

        viewModel.dataHoraDevolucaoTexto.observe(this, Observer {
            tvDetalhesDevolucao.text = it
        })

        viewModel.localRetiradaTexto.observe(this, Observer {
            tvDetalhesHoraRetirada.text = String.format(getString(R.string.tv_agenciaRetirada), it)
        })

        viewModel.localDevolucaoTexto.observe(this, Observer {
            tvDetalhesHoraDevolucao.text = String.format(getString(R.string.tv_agenciaDevolucao), it)
        })
    }

    private fun statusBarTransparente() {
        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window?.statusBarColor = Color.TRANSPARENT
    }

    private fun tipoCategoriaDescricao(categoria: CategoriaEnum): String {
        return when (categoria) {
            CategoriaEnum.BASICO -> getString(R.string.ct_basico)
            CategoriaEnum.COMPLETO -> getString(R.string.ct_completo)
            else -> getString(R.string.ct_Luxo)
        }
    }

    fun parametrosIniciais() {
        val veiculo = intent.getParcelableExtra<Veiculo>(PARAM_VEICULO) ?: return
        val minhasReservas = intent.getBooleanExtra(PARAM_MINHAS_RESERVAS,false)

        when {
            minhasReservas -> {
                val dataRetirada = intent.getStringExtra(PARAM_DATA_RETIRADA) ?: ""
                val dataDevolucao = intent.getStringExtra(PARAM_DATA_DEVOLUCAO) ?: ""
                val localRetirada = intent.getStringExtra(PARAM_LOCAL_RETIRADA) ?: ""
                val localDevolucao = intent.getStringExtra(PARAM_LOCAL_DEVOLUCAO) ?: ""

                viewModel.initDetalhesReserva(veiculo,minhasReservas, dataRetirada, dataDevolucao, localRetirada, localDevolucao)
            }
            else -> {
                val agencia = intent.getParcelableExtra<Agencia>(PARAM_AGENCIA)
                val dataRetirada = intent.getParcelableExtra<Horario>(PARAM_DATA_RETIRADA)
                val dataDevolucao = intent.getParcelableExtra<Horario>(PARAM_DATA_DEVOLUCAO)

                viewModel.init(veiculo,minhasReservas,agencia, dataRetirada, dataDevolucao)
            }
        }
    }

    companion object {
        private const val PARAM_VEICULO = "PARAM_VEICULO"
        private const val PARAM_MINHAS_RESERVAS = "PARAM_MINHAS_RESERVAS"
        private const val PARAM_DATA_RETIRADA = "PARAM_DATA_RETIRADA"
        private const val PARAM_DATA_DEVOLUCAO = "PARAM_DATA_DEVOLUCAO"
        private const val PARAM_AGENCIA = "PARAM_AGENCIA"
        private const val PARAM_LOCAL_RETIRADA = "PARAM_LOCAL_RETIRADA"
        private const val PARAM_LOCAL_DEVOLUCAO = "PARAM_LOCAL_DEVOLUCAO"
    }
}