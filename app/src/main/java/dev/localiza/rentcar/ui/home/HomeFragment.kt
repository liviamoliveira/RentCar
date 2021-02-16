package dev.localiza.rentcar.ui.home

import androidx.appcompat.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import dev.localiza.rentcar.R
import dev.localiza.rentcar.base.BaseViewModel
import dev.localiza.rentcar.base.extension.createLoadingDialog
import dev.localiza.rentcar.ui.reservas.listarAgenciaReserva.ListarAgenciaActivity
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class HomeFragment : Fragment() {

    private val viewModel by viewModel<HomeViewModel>()

    private val loadingAlert: AlertDialog? by lazy {
        createLoadingDialog()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
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

    private fun showMessage(message: String) {
        createDialog(message).show()
    }

    private fun createDialog(message: String): AlertDialog {
        return AlertDialog.Builder(requireContext()).setMessage(message).create()
    }

    private fun eventosClique() {
        btReservar.setOnClickListener {
            val intent = Intent(requireContext(), ListarAgenciaActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        statusBarTransparente()
    }

    private fun statusBarTransparente() {
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        activity?.window?.statusBarColor = Color.TRANSPARENT
    }
}

