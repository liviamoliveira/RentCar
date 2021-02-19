package dev.localiza.rentcar.ui.reservas.listarReservas.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.localiza.rentcar.R
import dev.localiza.rentcar.model.Reserva
import kotlinx.android.synthetic.main.item_reserva.view.*
import java.text.SimpleDateFormat
import java.util.*


internal class ListarReservasAdapter (private val onSelect: (Reserva) -> Unit) : ListAdapter<Reserva, ListarReservasAdapter.ViewHolder>(DIFF_CALLBACK) {

    private val formatData = "dd MMMM yyyy"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_reserva, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.itemView) {

            val simpleDateFormat = SimpleDateFormat(formatData, Locale.getDefault())
            val dataHoraRetirada = simpleDateFormat.format(item.dataRetirada)
            val dataHoraDevolucao = simpleDateFormat.format(item.dataDevolucao)

            tvNomeAgenciaMinhasReservas.text = item.localRetirada?.nome
            tvDataRetiradaDevolucao.text = "$dataHoraRetirada - $dataHoraDevolucao"

           cdMinhasReservas.setOnClickListener {
               onSelect(item)
           }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    object DIFF_CALLBACK : DiffUtil.ItemCallback<Reserva>() {
        override fun areItemsTheSame(oldItem: Reserva, newItem: Reserva): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Reserva, newItem: Reserva): Boolean {
            return oldItem == newItem
        }
    }
}
