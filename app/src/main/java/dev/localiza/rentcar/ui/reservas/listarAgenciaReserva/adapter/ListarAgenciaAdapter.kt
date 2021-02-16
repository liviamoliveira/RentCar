package dev.localiza.rentcar.ui.reservas.listarAgenciaReserva.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.localiza.rentcar.R
import dev.localiza.rentcar.model.Agencia
import kotlinx.android.synthetic.main.item_agencia.view.*

internal class ListarAgenciaAdapter(private val onSelect: (Agencia) -> Unit) : ListAdapter<Agencia, ListarAgenciaAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_agencia, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.itemView) {
            tvNomeAgencia.text = item.nome

            cdAgencias.setOnClickListener {
                onSelect(item)
            }

        }
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    object DIFF_CALLBACK : DiffUtil.ItemCallback<Agencia>() {
        override fun areItemsTheSame(oldItem: Agencia, newItem: Agencia): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Agencia, newItem: Agencia): Boolean {
            return oldItem == newItem
        }
    }
}
