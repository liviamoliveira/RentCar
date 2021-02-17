package dev.localiza.rentcar.ui.reservas.selecionarDataHoraReserva

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import dev.localiza.rentcar.R
import dev.localiza.rentcar.base.extension.DatePickerValidator
import dev.localiza.rentcar.base.extension.toCalendar
import dev.localiza.rentcar.model.Agencia
import dev.localiza.rentcar.model.Horario
import dev.localiza.rentcar.model.MovimentoTipo
import dev.localiza.rentcar.ui.reservas.listarVeiculos.ListarVeiculoActivity
import kotlinx.android.synthetic.main.activity_selecionar_data_hora.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*


class SelecionarDataHoraActivity : AppCompatActivity() {
    private val viewModel by viewModel<SelecionarDataHoraViewModel>()

    private lateinit var listaHorarios: MutableList<Horario>

    private val formatDataddMMMMyyyy = "dd-MM-yyyy"
    private val formatDatayyyyMMdd = "yyyy-MM-dd"
    private val formatTime = "HH:mm"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selecionar_data_hora)

        eventosClique()
        observers()
        parametrosIniciais()
    }

    override fun onStart() {
        super.onStart()
        statusBarTransparente()
    }

    private fun observers() {
        viewModel.agenciaSelecionada.observe(this, Observer {
            tvDetalhesRetirada.text = String.format(getString(R.string.tv_agenciaRetirada), it.nome)
            tvDetalhesDevolucao.text = String.format(getString(R.string.tv_agenciaDevolucao), it.nome)
        })

        viewModel.exibirHorarioRetirada.observe(this, Observer {
            tvHoraRetiradaSelecionada.text = it
        })

        viewModel.exibirHorarioDevolucao.observe(this, Observer {
            tvHoraDevolucaoSelecionada.text = it
        })

        viewModel.exibirDataRetirada.observe(this, Observer {
            val simpleDateFormat = SimpleDateFormat(formatDataddMMMMyyyy, Locale.getDefault())
            val dataFormatada = simpleDateFormat.format(it.time)
            tvDataRetiradaSelecionada.text = dataFormatada
        })

        viewModel.exibirDataDevolucao.observe(this, Observer {
            val simpleDateFormat = SimpleDateFormat(formatDataddMMMMyyyy, Locale.getDefault())
            val dataFormatada = simpleDateFormat.format(it.time)
            tvDataDevolucaoSelecionada.text = dataFormatada
        })
    }

    private fun eventosClique() {
        btAvancar.setOnClickListener {

            when {
                viewModel.getData(MovimentoTipo.RETIRADA) != null && viewModel.getData(MovimentoTipo.DEVOLUCAO) != null -> {
                    if(viewModel.getHorario(MovimentoTipo.RETIRADA) != null && viewModel.getHorario(MovimentoTipo.DEVOLUCAO) != null)
                        irParaListarVeiculos()
                    else
                        showSimpleDialog(getString(R.string.dialog_dataEhorario),"",false)
                }
                else -> showSimpleDialog(getString(R.string.dialog_dataEhorario),"",false)
            }

        }

        cdDataRetirada.setOnClickListener {
            selecionarData(MovimentoTipo.RETIRADA)
        }

        cdDataDevolucao.setOnClickListener {
            selecionarData(MovimentoTipo.DEVOLUCAO)
        }
    }

    private fun irParaListarVeiculos() {
        val intent = Intent(this, ListarVeiculoActivity::class.java)
        intent.putExtra(PARAM_AGENCIA, viewModel.getAgenciaSelecionada())
        val dtRetirada = validarTempoRetirada()
        intent.putExtra(PARAM_DATA_RETIRADA, dtRetirada)
        val dtDevolucao = validarTempoDevolucao()
        intent.putExtra(PARAM_DATA_DEVOLUCAO,dtDevolucao)
        startActivity(intent)
    }

    private fun validarTempoRetirada(): Horario? {
        val c = Calendar.getInstance();
        c.time = viewModel.getHorario(MovimentoTipo.RETIRADA)?.dataHora
        c[Calendar.HOUR_OF_DAY] = viewModel.getHorario(MovimentoTipo.RETIRADA)?.exibicao?.split(":")!![0]?.toInt()
        c[Calendar.MINUTE] = 0
        c[Calendar.SECOND] = 0

        val date = viewModel.getHorario(MovimentoTipo.RETIRADA)?.dataHora
        date?.time = c.timeInMillis

        return Horario(viewModel.getHorario(MovimentoTipo.RETIRADA)?.exibicao!!,date!!)
    }

    private fun validarTempoDevolucao(): Horario? {
        val c = Calendar.getInstance();
        c.time = viewModel.getHorario(MovimentoTipo.DEVOLUCAO)?.dataHora
        c[Calendar.HOUR_OF_DAY] = viewModel.getHorario(MovimentoTipo.DEVOLUCAO)?.exibicao?.split(":")!![0]?.toInt()
        c[Calendar.MINUTE] = 0
        c[Calendar.SECOND] = 0

        val date = viewModel.getHorario(MovimentoTipo.DEVOLUCAO)?.dataHora
        date?.time = c.timeInMillis

        return Horario(viewModel.getHorario(MovimentoTipo.DEVOLUCAO)?.exibicao!!,date!!)
    }

    private fun statusBarTransparente() {
        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window?.statusBarColor = Color.TRANSPARENT
    }

    fun parametrosIniciais() {
        val agencia = intent.getParcelableExtra<Agencia>(PARAM_AGENCIA) ?: return
        viewModel.init(agencia)
    }

    private fun selecionarData(movimento: MovimentoTipo) {
        val datePicker = exibirCalendario(movimento, Date(), Date())
        supportFragmentManager?.let { datePicker.show(it, movimento.toString()) }
    }

    private fun exibirCalendario(movimento: MovimentoTipo, dataMinima: Date?, data: Date?): MaterialDatePicker<Long> {

        val calendarConstraints = CalendarConstraints.Builder().apply {
            if (data != null) setOpenAt(data.time)

            if (dataMinima != null) setStart(dataMinima.time)

            setValidator(DatePickerValidator(dataMinima,dataMinima))

        }.build()

        val selectedDate: Date? = data ?: dataMinima

        return MaterialDatePicker.Builder.datePicker()
                .setSelection(selectedDate?.toCalendar()?.timeInMillis)
                .setCalendarConstraints(calendarConstraints)
                .build()
                .apply {
                    addOnPositiveButtonClickListener {
                        adicionarHorarios(it,movimento)
             }
         }
      }

    private fun adicionarHorarios(it: Long, movimento: MovimentoTipo) {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = it
            add(Calendar.DAY_OF_YEAR, 1)
        }

        val dataSelecionada = calendar.time

       if(movimento == MovimentoTipo.DEVOLUCAO){
           if(calendar.time >= viewModel.getData(MovimentoTipo.RETIRADA)!!){
               verificarData(movimento, dataSelecionada)
           }
           else{
               showSimpleDialog(getString(R.string.dialog_infDataDevolucao),"",false)
           }
       }
        else{
           verificarData(movimento, dataSelecionada)
       }
    }

    private fun verificarData(movimento: MovimentoTipo, dataSelecionada: Date) {
        viewModel.setData(movimento, dataSelecionada)

        val simpleDateFormat = SimpleDateFormat(formatDatayyyyMMdd, Locale.getDefault())
        val dataSelecionadaFormatada = simpleDateFormat.format(dataSelecionada.time)

        listaHorarios = mutableListOf()

        val horaMinima = Date().toCalendar().apply { add(Calendar.HOUR_OF_DAY, 1) }.time

        val cal = Calendar.getInstance()
        cal[Calendar.HOUR_OF_DAY] = 0
        cal[Calendar.MINUTE] = 0
        cal[Calendar.SECOND] = 0

        val dataInicioDia = cal[Calendar.DATE]

        val dataHoje = simpleDateFormat.format(Date())

        while (cal[Calendar.DATE] === dataInicioDia) {
            verificarHorarios(cal, dataHoje, dataSelecionadaFormatada, horaMinima, dataSelecionada)
        }

        selecaoHorarios(movimento, listaHorarios)
    }

    private fun verificarHorarios(cal: Calendar, dataHoje: String, dataSelecionadaFormatada: String?, horaMinima: Date, dataSelecionada: Date) {
        cal.add(Calendar.HOUR, 1)

        val simpleDateFormat = SimpleDateFormat(formatTime, Locale.getDefault())
        val data = simpleDateFormat.format(cal.time)

        if (dataHoje == dataSelecionadaFormatada) {
            if (cal.time > horaMinima) {
                listaHorarios.add(Horario(data, dataSelecionada))
            }
        } else {
            listaHorarios.add(Horario(data, dataSelecionada))
        }
    }

    private fun selecaoHorarios(movimento: MovimentoTipo, horarios: List<Horario>?) {
        val dialogTitle = getString(R.string.dialog_horarioDisp)

        if (horarios.isNullOrEmpty()) {
            showSimpleDialog(
                    message = getString(R.string.tv_data),
                    title = dialogTitle
            )
            return
        }

        val items = horarios.map { it.exibicao }.toTypedArray()
        val checkedItem = viewModel.getHorario(movimento)?.let { horarios.indexOf(it) } ?: -1

        alertHorario(dialogTitle, items, checkedItem, movimento, horarios)
    }

    fun showSimpleDialog(message: String, title: String? = null, isCancelable: Boolean = true, positiveButtonListener: DialogInterface.OnClickListener? = null) {
        AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, positiveButtonListener)
                .setCancelable(isCancelable)
                .show()
    }

    private fun alertHorario(dialogTitle: String, items: Array<String>, checkedItem: Int, movimentoTipo: MovimentoTipo, horarios: List<Horario>) {
        AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle(dialogTitle)
                .setSingleChoiceItems(items, checkedItem) { dialog, which ->
                    viewModel.setHorario(
                            movimentoTipo, horarios[which]
                    )
                    dialog.dismiss()
                }
                .show()
    }

    companion object {
        private const val PARAM_AGENCIA = "PARAM_AGENCIA"
        private const val PARAM_DATA_RETIRADA = "PARAM_DATA_RETIRADA"
        private const val PARAM_DATA_DEVOLUCAO = "PARAM_DATA_DEVOLUCAO"
    }
}