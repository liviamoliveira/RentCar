package dev.localiza.rentcar.ui.reservas.listarReservas

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dev.localiza.rentcar.R
import dev.localiza.rentcar.base.sharedPreference.Authentication
import dev.localiza.rentcar.model.Reserva
import dev.localiza.rentcar.ui.reservas.detalharReserva.DetalharReservasActivity
import dev.localiza.rentcar.ui.reservas.listarReservas.adapter.ListarReservasAdapter
import kotlinx.android.synthetic.main.fragment_listar_reservas.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
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
        parametrosIniciais()
    }

    private fun observers() {
       viewModel.selecaoMinhasReservas.observe(requireActivity(), androidx.lifecycle.Observer {
           val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.getDefault())
           val dataHoraRetirada = simpleDateFormat.format(it.dataRetirada)
           val dataHoraDevolucao = simpleDateFormat.format(it.dataDevolucao)

           irParaDetalhesReserva(it, dataHoraRetirada, dataHoraDevolucao)
       })

        viewModel.buscarReservaSucesso.observe(requireActivity(), Observer {
            listarReservasAdapter.submitList(it)
        })
    }

    private fun eventosClique() {
        rvReservas.layoutManager = LinearLayoutManager(requireContext())
        rvReservas.adapter = listarReservasAdapter
    }

    private fun irParaDetalhesReserva(it: Reserva, dataHoraRetirada: String, dataHoraDevolucao: String) {
        val intent = Intent(requireContext(), DetalharReservasActivity::class.java)
        intent.putExtra(PARAM_MINHAS_RESERVAS, true)
        intent.putExtra(PARAM_VEICULO, it.veiculo)
        intent.putExtra(PARAM_DATA_RETIRADA, dataHoraRetirada)
        intent.putExtra(PARAM_DATA_DEVOLUCAO, dataHoraDevolucao)
        intent.putExtra(PARAM_LOCAL_RETIRADA, it.localRetirada?.nome)
        intent.putExtra(PARAM_LOCAL_DEVOLUCAO, it.localDevolucao?.nome)
        startActivity(intent)
    }

    private fun parametrosIniciais() {
         val reserva = arguments?.getParcelableArrayList(PARAM_RESERVA) ?: emptyList<Reserva>()

        if(reserva.isNotEmpty()){
            listarReservasAdapter.submitList(reserva)
        }
        else{
            val shared = Authentication.getInstance(requireContext())
            val cpf = shared?.getData(PARAM_KEY_CPF)

            viewModel.getBuscarReservaCPF(cpf.toString())

           /* val veiculo = Veiculo(
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
                    2, agencia,1,1
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
                    2, agencia2,1,1
            )

            val listaReserva = listOf(minhasReservas,minhasReservas2)

            listarReservasAdapter.submitList(listaReserva)*/
        }
    }

    companion object {
        private const val PARAM_VEICULO = "PARAM_VEICULO"
        private const val PARAM_DATA_RETIRADA = "PARAM_DATA_RETIRADA"
        private const val PARAM_DATA_DEVOLUCAO = "PARAM_DATA_DEVOLUCAO"
        private const val PARAM_MINHAS_RESERVAS = "PARAM_MINHAS_RESERVAS"
        private const val PARAM_LOCAL_RETIRADA = "PARAM_LOCAL_RETIRADA"
        private const val PARAM_LOCAL_DEVOLUCAO = "PARAM_LOCAL_DEVOLUCAO"
        private const val PARAM_RESERVA = "PARAM_RESERVA"
        private const val PARAM_KEY_CPF = "PARAM_KEY_CPF"
    }
}