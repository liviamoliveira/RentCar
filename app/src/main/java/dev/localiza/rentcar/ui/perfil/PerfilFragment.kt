package dev.localiza.rentcar.ui.perfil

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import dev.localiza.rentcar.MainActivity
import dev.localiza.rentcar.R
import dev.localiza.rentcar.base.BaseViewModel
import dev.localiza.rentcar.base.extension.createLoadingDialog
import dev.localiza.rentcar.base.sharedPreference.Authentication
import kotlinx.android.synthetic.main.fragment_perfil.*
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class PerfilFragment : Fragment() {

    private val viewModel by viewModel<PerfilViewModel>()

    private val loadingAlert: AlertDialog? by lazy {
        createLoadingDialog()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_perfil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        eventosClique()
        observers()
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
    }

    private fun eventosClique() {
        btSair.setOnClickListener {
            removerInformacoesUsuario()

            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun removerInformacoesUsuario(){
        val shared = Authentication.getInstance(requireContext())
        shared?.saveData(PARAM_KEY_TOKEN, null)
        shared?.saveData(PARAM_KEY_NOME, null)
        shared?.saveData(PARAM_KEY_CODIGO, null)
    }

    private fun showMessage(message: String) {
        showSimpleDialog(message,"",false)
    }

    fun showSimpleDialog(message: String, title: String? = null, isCancelable: Boolean = true, positiveButtonListener: DialogInterface.OnClickListener? = null) {
        AlertDialog.Builder(requireContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, positiveButtonListener)
                .setCancelable(isCancelable)
                .show()
    }

    companion object{
        private const val PARAM_KEY_TOKEN = "PARAM_KEY_TOKEN"
        private const val PARAM_KEY_NOME = "PARAM_KEY_NOME"
        private const val PARAM_KEY_CODIGO = "PARAM_KEY_CODIGO"
    }
}