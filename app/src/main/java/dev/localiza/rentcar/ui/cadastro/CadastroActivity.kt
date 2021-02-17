package dev.localiza.rentcar.ui.cadastro

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import dev.localiza.rentcar.MainActivity
import dev.localiza.rentcar.R
import kotlinx.android.synthetic.main.activity_cadastro.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CadastroActivity : AppCompatActivity() {

    private val viewModel by viewModel<CadastroViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        eventosClique()
    }

    override fun onStart() {
        super.onStart()
        statusBarTransparente()
    }

    private fun eventosClique() {
        btnCadastrar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun statusBarTransparente() {
        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window?.statusBarColor = Color.TRANSPARENT
    }
}