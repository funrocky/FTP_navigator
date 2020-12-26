package cn.edu.sicnu.tfp_navigator.ui.connectionLake

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.edu.sicnu.tfp_navigator.Connection
import cn.edu.sicnu.tfp_navigator.connList

class ConnectionLakeViewModel : ViewModel() {

    private val _connlistModel = MutableLiveData<MutableList<Connection>>().apply {
        this.value= connList
    }

    val connlistModel: LiveData<MutableList<Connection>> = _connlistModel


    private val _text2= MutableLiveData<Connection>().apply{
        this.value = Connection("wwe","110.0.0.1",21," ",1,true,"fq","abc456")

    }
    val text2: MutableLiveData<Connection> = _text2
}