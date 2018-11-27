package com.example.candi.alkicar

class CocheArray {
    // DEBE LLAMARSE IGUAL QUE EN EL FICHERO JSON
    var Coches: ArrayList<Coche> = ArrayList<Coche>()

    fun addCoche(c:Coche){
        Coches.add(c)
    }

    fun getArray () : ArrayList<Coche>{
        return Coches
    }
}