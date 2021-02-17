package dev.localiza.rentcar.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.localiza.rentcar.MainActivity
import dev.localiza.rentcar.R
import dev.localiza.rentcar.ui.cadastro.CadastroActivity
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val viewModel by viewModel<LoginViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        eventosClique()
    }

    private fun eventosClique() {

        btEntrar.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }
        btnCadastrarLogin.setOnClickListener {
            val intent = Intent(requireContext(), CadastroActivity::class.java)
            startActivity(intent)
        }
    }
}