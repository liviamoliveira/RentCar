package dev.localiza.rentcar.ui.reservas.consultarReserva

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import dev.localiza.rentcar.R
import dev.localiza.rentcar.base.BaseViewModel
import dev.localiza.rentcar.base.extension.createLoadingDialog
import dev.localiza.rentcar.base.sharedPreference.Authentication
import dev.localiza.rentcar.ui.reservas.listarAgenciaReserva.ListarAgenciaActivity
import dev.localiza.rentcar.ui.reservas.listarReservas.ListarReservasFragment
import kotlinx.android.synthetic.main.fragment_consultar_reserva.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConsultarReservaFragment : Fragment() {

    private val viewModel by viewModel<ConsultarReservaViewModel>()

    private val loadingAlert: AlertDialog? by lazy {
        createLoadingDialog()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_consultar_reserva, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        eventosClique()
        verificarUsuarioLogado()
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

        viewModel.buscarReservaSucesso.observe(requireActivity(), Observer {
            val listaReservas = ListarReservasFragment()

            val bundle = Bundle()
            bundle.putParcelableArrayList(PARAM_RESERVA, arrayListOf(*it.toTypedArray()))
            listaReservas.arguments = bundle


            activity?.supportFragmentManager?.beginTransaction()
                    ?.add(R.id.nav_host_fragment, listaReservas)
                    ?.commit()
        })
    }

    fun showSimpleDialog(message: String, title: String? = null, isCancelable: Boolean = true, positiveButtonListener: DialogInterface.OnClickListener? = null) {
        AlertDialog.Builder(requireContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, positiveButtonListener)
                .setCancelable(isCancelable)
                .show()
    }

    private fun showMessage(message: String) {
        showSimpleDialog(message,"",false)
    }


    private fun verificarUsuarioLogado() {
        val shared = Authentication.getInstance(requireContext())
        val token = shared?.getData(PARAM_KEY_TOKEN)

        if(token != null){
            activity?.supportFragmentManager?.beginTransaction()
                    ?.add(R.id.nav_host_fragment, ListarReservasFragment())
                    ?.commit()
        }
    }

    private fun eventosClique() {
        btCriarReserva.setOnClickListener {
            val intent = Intent(requireContext(), ListarAgenciaActivity::class.java)
            startActivity(intent)
        }

        btConsultarReserva.setOnClickListener {
            val shared = Authentication.getInstance(requireContext())
            val token = shared?.getData(PARAM_KEY_TOKEN)
            val cpf = shared?.getData(PARAM_KEY_CPF)

            when {
                token != null -> {
                    viewModel.getBuscarReservaCPF(cpf.toString())
                }
                else -> {
                    val cpfNaoLogado = etCPF.text
                    viewModel.getBuscarReservaCPF(cpfNaoLogado.toString())
                }
            }
        }
    }

    companion object{
        private const val PARAM_KEY_TOKEN = "PARAM_KEY_TOKEN"
        private const val PARAM_KEY_CPF = "PARAM_KEY_CPF"
        private const val PARAM_RESERVA = "PARAM_RESERVA"
    }
}
