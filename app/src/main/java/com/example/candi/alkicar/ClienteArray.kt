package com.example.candi.alkicar

class ClienteArray {
    var Clientes: ArrayList<Clientes> = ArrayList<Clientes>()

    fun addCliente(c:Clientes){
        Clientes.add(c)
    }

    fun getArray () : ArrayList<Clientes>{
        return Clientes
    }
}