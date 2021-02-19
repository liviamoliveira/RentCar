package dev.localiza.rentcar.ui.reservas.dadosReserva

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dev.localiza.rentcar.R
import dev.localiza.rentcar.base.BaseViewModel
import dev.localiza.rentcar.base.extension.createLoadingDialog
import dev.localiza.rentcar.base.sharedPreference.Authentication
import dev.localiza.rentcar.model.*
import dev.localiza.rentcar.model.Horario
import dev.localiza.rentcar.ui.reservas.confirmarReserva.ConfirmarReservaActivity
import kotlinx.android.synthetic.main.activity_dados_pessoais_reserva.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class InformarDadosReservaActivity: AppCompatActivity() {

    private val viewModel by viewModel<InformarDadosReservaViewModel>()

    private val formatData = "dd-MM-yyyy"

    private val loadingAlert: AlertDialog? by lazy {
        createLoadingDialog()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dados_pessoais_reserva)

        parametrosIniciais()
        eventosClique()
        observers()
    }

    override fun onStart() {
        super.onStart()
        statusBarTransparente()
    }

    private fun observers() {
        viewModel.sucessoReserva.observe(this, Observer {
            irParaConfirmacao(it)
        })

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
        btConfirmarReserva.setOnClickListener {
            validarReserva()
        }
    }

    private fun validarReserva() {
        val nome = etNome.text.toString()
        val cpf = etCPF.text.toString()
        val cep = etCep.text.toString()
        val logradouro = etLogradouro.text.toString()
        val complemento = etComplemento.text.toString()
        val bairro = etBairro.text.toString()
        val estado = etEstado.text.toString()
        val cidade = etCidade.text.toString()
        val numero = etNumero.text.toString()
        val celular = etCelular.text.toString()
        val email = etEmail.text.toString()

        val listaCampos = listOf(nome, cpf, cep, logradouro, complemento, bairro, estado, cidade, numero, celular, email)

        val camposOk = verificarCampos(listaCampos)

        if (camposOk) {
            val shared = Authentication.getInstance(this)
            val codigo = shared?.getData(PARAM_KEY_CODIGO)

            adicionarReserva(codigo)
        }
    }

    private fun adicionarReserva(codigo: String?) {
        if (codigo != null) {
            val reserva = Reserva(0,
                    viewModel.getVeiculo()!!.id,
                    null, 1.0, 1.0,
                    1, null,
                    2, null,
                    viewModel.getDataRetirada()?.dataHora, viewModel.getAgencia()?.id!!,
                    null, viewModel.getDataDevolucao()?.dataHora,
                    viewModel.getAgencia()?.id!!, null,
                    1, 1)

            viewModel.salvarReserva(reserva)
        } else {

            val reserva = Reserva(0,
                    1, null, 1.0, 1.0,
                    1, null, 2,
                    null, viewModel.getDataRetirada()?.dataHora,2,
                    null,
                    viewModel.getDataDevolucao()?.dataHora,
                    2, null, 1, 1)

            viewModel.salvarReserva(reserva)
        }
    }

    private fun irParaConfirmacao(id: Int) {
        val intent = Intent(this, ConfirmarReservaActivity::class.java)
        intent.putExtra(PARAM_ID_RESERVA, id)
        startActivity(intent)
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

    private fun preencherDados(cliente: Cliente?) {
        etNome.setText(cliente?.nome.toString())
        etCPF.setText(cliente?.cpf.toString())
        etCep.setText(cliente?.cep.toString())
        etLogradouro.setText(cliente?.logradouro.toString())
        etComplemento.setText(cliente?.complemento.toString())
        etBairro.setText(cliente?.bairro.toString())
        etEstado.setText(cliente?.uf.toString())
        etCidade.setText(cliente?.cidade.toString())
        etNumero.setText(cliente?.numero.toString())
        etCelular.setText(cliente?.celular.toString())
        etEmail.setText(cliente?.email.toString())

        val simpleDateFormat = SimpleDateFormat(formatData, Locale.getDefault())
        val dataFormatada = simpleDateFormat.format(cliente?.data_nasc)
        etDataNascimento.setText(dataFormatada)
    }

    private fun parametrosIniciais() {
        val cliente = intent.getParcelableExtra<Cliente>(PARAM_DADOS_LOGADO)
        val agencia = intent.getParcelableExtra<Agencia>(PARAM_AGENCIA)
        val dataRetirada = intent.getParcelableExtra<Horario>(PARAM_DATA_RETIRADA)
        val dataDevolucao = intent.getParcelableExtra<Horario>(PARAM_DATA_DEVOLUCAO)
        val veiculo = intent.getParcelableExtra<Veiculo>(PARAM_VEICULO)

        if (dataRetirada != null) {
            if (dataDevolucao != null) {
                viewModel.init(agencia, dataRetirada,dataDevolucao,veiculo)
            }
        }

        val shared = Authentication.getInstance(this)
        val codigo = shared?.getData(PARAM_KEY_CODIGO)

        if(codigo != null){
            preencherDados(cliente)
        }

    }

    companion object {
        private const val PARAM_DADOS_LOGADO = "PARAM_DADOS_LOGADO"
        private const val PARAM_DATA_RETIRADA = "PARAM_DATA_RETIRADA"
        private const val PARAM_DATA_DEVOLUCAO = "PARAM_DATA_DEVOLUCAO"
        private const val PARAM_AGENCIA = "PARAM_AGENCIA"
        private const val PARAM_KEY_CODIGO = "PARAM_KEY_CODIGO"
        private const val PARAM_VEICULO = "PARAM_VEICULO"
        private const val PARAM_ID_RESERVA = "PARAM_ID_RESERVA"
    }
}