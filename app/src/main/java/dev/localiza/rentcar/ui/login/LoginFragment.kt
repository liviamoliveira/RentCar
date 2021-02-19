package dev.localiza.rentcar.ui.login

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
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
import dev.localiza.rentcar.model.Login
import dev.localiza.rentcar.ui.cadastro.CadastroActivity
import dev.localiza.rentcar.ui.perfil.PerfilFragment
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val viewModel by viewModel<LoginViewModel>()

    private val loadingAlert: AlertDialog? by lazy {
        createLoadingDialog()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        eventosClique()
        observers()
        verificarUsuarioLogado()
    }

    private fun observers() {
        viewModel.showError.observe(this, Observer {
            showMessage("Login inválido.")
        })

        viewModel.state.observe(this, Observer { state ->
            when (state) {
                BaseViewModel.State.Default -> loadingAlert?.hide()
                BaseViewModel.State.Loading -> loadingAlert?.show()
            }
        })

        viewModel.loginSucesso.observe(this, Observer {
            salvarInformacoesUsuario(it.nome.toString(), it.id)
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        })
    }

    private fun verificarUsuarioLogado() {
        val shared = Authentication.getInstance(requireContext())
        val token = shared?.getData(PARAM_KEY_TOKEN)

        if(token != null){
            activity?.supportFragmentManager?.beginTransaction()
                    ?.add(R.id.nav_host_fragment, PerfilFragment())
                    ?.commit()
        }
    }

    private fun showMessage(message: String) {
        showSimpleDialog(message,"Login",false)
    }

    fun showSimpleDialog(message: String, title: String? = null, isCancelable: Boolean = true, positiveButtonListener: DialogInterface.OnClickListener? = null) {
        AlertDialog.Builder(requireContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, positiveButtonListener)
                .setCancelable(isCancelable)
                .show()
    }

    private fun eventosClique() {
        btEntrar.setOnClickListener {
            val senha = etSenha.text.toString()
            val usuario = etCPF.text.toString()

            when {
                senha.isNotBlank() && usuario.isNotBlank() -> {
                    val login = Login(usuario,senha)

                    viewModel.logar(login)
                }
                else -> {
                    showSimpleDialog("Preencha os campos login e senha","Login",false)
                }
            }
        }

        btnCadastrarLogin.setOnClickListener {
            val intent = Intent(requireContext(), CadastroActivity::class.java)
            startActivity(intent)
        }
    }

    private fun salvarInformacoesUsuario(nome: String, idUsuario: Int): Authentication? {
        val shared = Authentication.getInstance(requireContext())
        shared?.saveData(PARAM_KEY_TOKEN, viewModel.gerarToken())
        shared?.saveData(PARAM_KEY_NOME, nome)
        shared?.saveData(PARAM_KEY_CODIGO, idUsuario.toString())
        return shared
    }

    companion object{
        private const val PARAM_KEY_TOKEN = "PARAM_KEY_TOKEN"
        private const val PARAM_KEY_NOME = "PARAM_KEY_NOME"
        private const val PARAM_KEY_CODIGO = "PARAM_KEY_CODIGO"
    }
}