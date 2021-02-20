package dev.localiza.rentcar.ui.cadastro

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dev.localiza.rentcar.R
import dev.localiza.rentcar.base.BaseViewModel
import dev.localiza.rentcar.base.extension.createLoadingDialog
import dev.localiza.rentcar.model.Cliente
import kotlinx.android.synthetic.main.activity_cadastro.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class CadastroActivity : AppCompatActivity() {

    private val viewModel by viewModel<CadastroViewModel>()

    private val loadingAlert: AlertDialog? by lazy {
        createLoadingDialog()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        eventosClique()
        observers()
    }

    override fun onStart() {
        super.onStart()
        statusBarTransparente()
    }

    private fun observers() {
        viewModel.showError.observe(this, Observer {
            showMessage("Não foi possível realizar o cadastro.")
        })

        viewModel.state.observe(this, Observer { state ->
            when (state) {
                BaseViewModel.State.Default -> loadingAlert?.hide()
                BaseViewModel.State.Loading -> loadingAlert?.show()
            }
        })

        viewModel.sucessoCadastro.observe(this, Observer {
            finish()
        })
    }

    private fun eventosClique() {
        btnCadastrar.setOnClickListener {

            val nome = etNome.text.toString()
            val cpf = etCPF.text.toString()
            val cep = etCep.text.toString()
            val logradouro = etLogradouro.text.toString()
            val complemento = etComplemento.text.toString()
            val bairro = etBairro.text.toString()
            val estado = etEstado.text.toString()
            val cidade = etCidade.text.toString()
            val senha = etSenha.text.toString()
            val numero = etNumero.text.toString()
            val celular = etCelular.text.toString()
            val email = etEmail.text.toString()

            val listaCampos = listOf(nome, cpf,cep,logradouro,complemento,bairro,estado,cidade,senha,numero, celular,email)

            val camposOk = verificarCampos(listaCampos)

             if(camposOk){
                 val cliente = Cliente(0, nome, cpf, Date(), cep, logradouro, numero.toInt(), estado, complemento, bairro, cidade,  cpf, senha,celular,email)
                 viewModel.salvarCliente(cliente)
             }
        }
    }

    private fun verificarCampos(listaCampos: List<String>): Boolean {
        for (campo in listaCampos) {
           val campoOk = validar(campo)
            if(!campoOk)
                return false
        }
        return true
    }

    private fun validar(value: String): Boolean {
        if (value.isBlank()) {
            showSimpleDialog("Existe(m) campo(s) não preenchido(s).","Cadastro",false)
            return false
        }
        return true
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
}