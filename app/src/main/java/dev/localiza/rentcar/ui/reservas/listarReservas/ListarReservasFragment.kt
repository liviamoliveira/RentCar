package dev.localiza.rentcar.ui.reservas.listarReservas

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dev.localiza.rentcar.R
import dev.localiza.rentcar.model.*
import dev.localiza.rentcar.ui.reservas.detalharReservas.DetalharReservasActivity
import dev.localiza.rentcar.ui.reservas.listarReservas.adapter.ListarReservasAdapter
import kotlinx.android.synthetic.main.fragment_listar_reservas.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class ListarReservasFragment : Fragment() {

    private val viewModel by viewModel<ListarReservasViewModel>()

    private val listarReservasAdapter by lazy {
        ListarReservasAdapter {
             viewModel.selecaoMinhasReservas(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_listar_reservas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        eventosClique()
        observers()
    }

    private fun observers() {
       viewModel.selecaoMinhasReservas.observe(requireActivity(), androidx.lifecycle.Observer {
           val intent = Intent(requireContext(), DetalharReservasActivity::class.java)
           intent.putExtra(PARAM_MINHAS_RESERVAS, true)
           intent.putExtra(PARAM_VEICULO, it.veiculo)
           startActivity(intent)
       })
    }

    private fun eventosClique() {
        rvReservas.layoutManager = LinearLayoutManager(requireContext())
        rvReservas.adapter = listarReservasAdapter

        val veiculo = Veiculo(
            1, "ABC",
            45.0,
            50,
            2,
            MarcaVeiculo("Chevrolet"),
            ModeloVeiculo("Onix"),
            2020,
            CategoriaEnum.BASICO,
            CombustivelEnum.GASOLINA,
            "https://revistacarro.com.br/wp-content/uploads/2018/05/chevrolet_onix_joy1.png"
        )

        val veiculo3 = Veiculo(3,"ABC",
            150.0,
            50,
            2,
            MarcaVeiculo("MERCEDES-BENZ"),
            ModeloVeiculo("Mercedes"),
            2020,
            CategoriaEnum.LUXO,
            CombustivelEnum.GASOLINA,
            "https://www.localiza.com/brasil-site/geral/Frota/MCEX.png"
        )

        val veiculoAgencia = VeiculoAgencia(1, 1, veiculo)

        val agencia = Agencia(1, "ABC", "Agencia Lourde", listOf(veiculoAgencia))
        val agencia2 = Agencia(1, "ABC", "Agência Cristiano Machado", listOf(veiculoAgencia))

        val enderecoUsuario = EnderecoUsuario(
            "31160-260",
            "Rua Maura",
            "Ipiranga",
            "202",
            "506",
            "BH",
            "MG"
        )


        val registro = RegistroUsuario(
            "MG-16031699",
            "Livia",
            Date(),
            TipoUsuarioEnum.CLIENTE,
            2,
            enderecoUsuario
        )


        val usuario = Usuario(
            "ABCD",
            "ABCD",
            1,
            registro
        )

        val minhasReservas = Reserva(
            1,
            1,
            veiculo,
            200.0,
            2500.0, 1,
            usuario, 1,
            usuario, Date(),
            1,
            agencia,
            Date(),
            2, agencia
        )

        val minhasReservas2 = Reserva(
            1,
            1,
            veiculo3,
            200.0,
            2500.0, 1,
            usuario, 1,
            usuario, Date(),
            1,
            agencia2,
            Date(),
            2, agencia2
        )

        val listaReserva = listOf(minhasReservas,minhasReservas2)

        listarReservasAdapter.submitList(listaReserva)
    }

    companion object {
        private const val PARAM_VEICULO = "PARAM_VEICULO"
        private const val PARAM_MINHAS_RESERVAS = "PARAM_MINHAS_RESERVAS"
    }
}