package dev.localiza.rentcar.ui.reservas.listarAgenciaReserva

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dev.localiza.rentcar.R
import dev.localiza.rentcar.base.BaseViewModel
import dev.localiza.rentcar.base.extension.createLoadingDialog
import dev.localiza.rentcar.ui.reservas.listarAgenciaReserva.adapter.ListarAgenciaAdapter
import dev.localiza.rentcar.ui.reservas.selecionarDataHoraReserva.SelecionarDataHoraActivity
import kotlinx.android.synthetic.main.activity_listar_agencias.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListarAgenciaActivity: AppCompatActivity() {

    private val viewModel by viewModel<ListarAgenciaViewModel>()

    private val loadingAlert: AlertDialog? by lazy {
        createLoadingDialog()
    }

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
        viewModel.showError.observe(this, Observer {
            showMessage(it)
        })

        viewModel.state.observe(this, Observer { state ->
            when (state) {
                BaseViewModel.State.Default -> loadingAlert?.hide()
                BaseViewModel.State.Loading -> loadingAlert?.show()
            }
        })

        viewModel.selecaoAgencia.observe(this, androidx.lifecycle.Observer {
            val intent = Intent(this, SelecionarDataHoraActivity::class.java)
            intent.putExtra(PARAM_AGENCIA, it)
            startActivity(intent)
        })

        viewModel.sucessoListaAgencias.observe(this, Observer {
            agenciaAdapter.submitList(it)
        })
    }


    private fun eventosClique() {
        rvAgencia.layoutManager = LinearLayoutManager(this)
        rvAgencia.adapter = agenciaAdapter

        viewModel.buscarAgencias()
    }

    fun showSimpleDialog(message: String, title: String? = null, isCancelable: Boolean = true, positiveButtonListener: DialogInterface.OnClickListener? = null) {
        AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, positiveButtonListener)
                .setCancelable(isCancelable)
                .show()
    }

    private fun showMessage(message: String) {
        showSimpleDialog(message,"",false)
    }

    private fun statusBarTransparente() {
        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window?.statusBarColor = Color.TRANSPARENT
    }

    companion object {
        private const val PARAM_AGENCIA = "PARAM_AGENCIA"
    }

}