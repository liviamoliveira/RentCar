package dev.localiza.rentcar.ui.cadastro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dev.localiza.rentcar.R

class CadastroFragment : Fragment() {

    private lateinit var cadastroViewModel: CadastroViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        cadastroViewModel =
                ViewModelProvider(this).get(CadastroViewModel::class.java)
        inicializarView()

        return inflater.inflate(R.layout.fragment_cadastro, container, false)
    }

    private fun inicializarView() {

    }
}