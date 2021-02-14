package dev.localiza.rentcar.ui.reservas.detalharReservas

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import dev.localiza.rentcar.R
import dev.localiza.rentcar.model.CategoriaEnum
import dev.localiza.rentcar.model.Veiculo
import kotlinx.android.synthetic.main.activity_detalhar_veiculo.*
import kotlinx.android.synthetic.main.item_listar_veiculo.view.*
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
        viewModel.exibirVeiculo(veiculo)
    }

    companion object {
        private const val PARAM_VEICULO = "PARAM_VEICULO"
    }
}