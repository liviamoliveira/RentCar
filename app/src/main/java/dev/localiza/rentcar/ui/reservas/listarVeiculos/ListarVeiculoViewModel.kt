package dev.localiza.rentcar.ui.reservas.listarVeiculos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.localiza.rentcar.model.Veiculo

class EscolherVeiculoViewModel : ViewModel(){

    val selecaoVeiculo = MutableLiveData<Veiculo>()

    fun selecaoVeiculo(veiculo: Veiculo) {
        selecaoVeiculo.value = veiculo
    }
}