package dev.localiza.rentcar.ui.reservas.listarVeiculos.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.ViewTarget
import dev.localiza.rentcar.R
import dev.localiza.rentcar.model.CategoriaEnum
import dev.localiza.rentcar.model.Veiculo
import dev.localiza.rentcar.model.VeiculoAgencia
import kotlinx.android.synthetic.main.item_listar_veiculo.view.*
import kotlinx.android.synthetic.main.item_listar_veiculo.view.ivCarro
import kotlinx.android.synthetic.main.item_listar_veiculo.view.tvCategoriaVeiculo
import kotlinx.android.synthetic.main.item_listar_veiculo.view.tvMala
import kotlinx.android.synthetic.main.item_listar_veiculo.view.tvMarcaVeiculo
import kotlinx.android.synthetic.main.item_listar_veiculo.view.tvModeloVeiculo


internal class ListarVeiculoAdapter(private val onSelect: (Veiculo) -> Unit) : ListAdapter<VeiculoAgencia, ListarVeiculoAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_listar_veiculo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.itemView) {

            popularDados(item.veiculo)
            carregarImagem(item.veiculo)

            btIrDetalhes.setOnClickListener {
                onSelect(item.veiculo)
            }

        }
    }

    private fun View.carregarImagem(item: Veiculo): ViewTarget<ImageView, Drawable> {
        return Glide.with(this).load(item.urlVeiculo)
            .placeholder(R.drawable.ic_logo)
            .into(ivCarro)
    }

    private fun View.popularDados(item: Veiculo) {
        tvMarcaVeiculo.text = item.marca.nome
        tvModeloVeiculo.text = item.modelo.nome
        tvMala.text = item.capacidadePortaMalas.toString()
        tvCategoriaVeiculo.text = tipoCategoriaDescricao(item.categoria)
        tvPreco.text =  "R$ " + "%.2f".format(item.valorHora).replace(".",",")
    }

    private fun tipoCategoriaDescricao(categoria: CategoriaEnum): String {
        return when (categoria) {
            CategoriaEnum.BASICO -> "Básico -"
            CategoriaEnum.COMPLETO -> "Completo -"
            else -> "Luxo -"
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    object DIFF_CALLBACK : DiffUtil.ItemCallback<VeiculoAgencia>() {
        override fun areItemsTheSame(oldItem: VeiculoAgencia, newItem: VeiculoAgencia): Boolean {
            return oldItem.veiculId == newItem.veiculId
        }

        override fun areContentsTheSame(oldItem: VeiculoAgencia, newItem: VeiculoAgencia): Boolean {
            return oldItem == newItem
        }
    }
}
