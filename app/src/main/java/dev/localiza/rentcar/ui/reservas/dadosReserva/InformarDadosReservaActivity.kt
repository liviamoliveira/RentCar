package dev.localiza.rentcar.ui.reservas.dadosReserva

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import dev.localiza.rentcar.R
import dev.localiza.rentcar.model.Agencia
import dev.localiza.rentcar.model.Cliente
import dev.localiza.rentcar.model.Horario
import dev.localiza.rentcar.ui.reservas.confirmarReserva.ConfirmarReservaActivity
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.activity_dados_pessoais_reserva.*
import kotlinx.android.synthetic.main.activity_dados_pessoais_reserva.etBairro
import kotlinx.android.synthetic.main.activity_dados_pessoais_reserva.etCPF
import kotlinx.android.synthetic.main.activity_dados_pessoais_reserva.etCelular
import kotlinx.android.synthetic.main.activity_dados_pessoais_reserva.etCep
import kotlinx.android.synthetic.main.activity_dados_pessoais_reserva.etCidade
import kotlinx.android.synthetic.main.activity_dados_pessoais_reserva.etComplemento
import kotlinx.android.synthetic.main.activity_dados_pessoais_reserva.etDataNascimento
import kotlinx.android.synthetic.main.activity_dados_pessoais_reserva.etEmail
import kotlinx.android.synthetic.main.activity_dados_pessoais_reserva.etEstado
import kotlinx.android.synthetic.main.activity_dados_pessoais_reserva.etLogradouro
import kotlinx.android.synthetic.main.activity_dados_pessoais_reserva.etNome
import kotlinx.android.synthetic.main.activity_dados_pessoais_reserva.etNumero
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class InformarDadosReservaActivity: AppCompatActivity() {

    private val viewModel by viewModel<InformarDadosReservaViewModel>()

    private val formatData = "dd-MM-yyyy"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dados_pessoais_reserva)

        parametrosIniciais()
        eventosClique()
    }


    override fun onStart() {
        super.onStart()
        statusBarTransparente()
    }

    private fun eventosClique() {
        btConfirmarReserva.setOnClickListener {

            irParaConfirmacao()
        }
    }

    private fun irParaConfirmacao() {
        val intent = Intent(this, ConfirmarReservaActivity::class.java)
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

        if (dataRetirada != null) {
            if (dataDevolucao != null) {
                viewModel.init(agencia, dataRetirada,dataDevolucao)
            }
        }

        preencherDados(cliente)
    }

    companion object {
        private const val PARAM_DADOS_LOGADO = "PARAM_DADOS_LOGADO"
        private const val PARAM_DATA_RETIRADA = "PARAM_DATA_RETIRADA"
        private const val PARAM_DATA_DEVOLUCAO = "PARAM_DATA_DEVOLUCAO"
        private const val PARAM_AGENCIA = "PARAM_AGENCIA"
    }
}