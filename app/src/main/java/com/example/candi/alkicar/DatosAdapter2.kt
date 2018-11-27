package com.example.candi.alkicar

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_datos.view.*
import kotlinx.android.synthetic.main.fragment_datos2.view.*

class DatosAdapter2(val items : ArrayList<Clientes>, val context: Context) : RecyclerView.Adapter<ViewHolder2>() {

    // Obtiene el número de datos
    override fun getItemCount(): Int {
        return items.size
    }

    //infla el layout activity_datos
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder2 {
        return ViewHolder2(LayoutInflater.from(context).inflate(R.layout.fragment_datos2, parent, false))
    }

    // carga datos del ArrayList aL TEXTVIEW view
    override fun onBindViewHolder(holder: ViewHolder2, position: Int) {
        holder?.tvDatosA?.text = items.get(position).DNI
        holder?.tvDatosB?.text = items.get(position).NOMBRE
        holder?.tvDatosC?.text = items.get(position).APELLIDO1
        holder?.tvDatosD?.text = items.get(position).APELLIDO2
        holder?.tvDatosE?.text = items.get(position).EMAIL
        holder?.tvDatosF?.text = items.get(position).TELEFONO
        holder?.tvDatosG?.text = items.get(position).MATRICULA
    }
}

class ViewHolder2 (view: View) : RecyclerView.ViewHolder(view) {
    // Mantiene el TextView
    val tvDatosA = view.textViewDni
    val tvDatosB = view.textViewNombre
    val tvDatosC = view.textViewApellido1
    val tvDatosD = view.textViewApellido2
    val tvDatosE = view.textViewEmail
    val tvDatosF = view.textViewTelefono
    val tvDatosG = view.textViewMatricula2
    val bcv = view.cardView2
}
