package com.example.candi.alkicar

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_datos.*
import kotlinx.android.synthetic.main.fragment_datos.view.*

class DatosAdapter(val items : ArrayList<Coche>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    // Obtiene el número de datos
    override fun getItemCount(): Int {
        return items.size
    }

    //infla el layout activity_datos
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_datos, parent, false))
    }

    // carga datos del ArrayList aL TEXTVIEW view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.tvDatosA?.text = items.get(position).MARCA
        holder?.tvDatosB?.text = items.get(position).MODELO
        holder?.ivDatos?.loadUrl(items.get(position).IMAGEN)
        holder?.tvDatosD?.text = items.get(position).MATRICULA
        holder?.tvDatosE?.text = "Disponible: " + items.get(position).DISPONIBILIDAD

        holder?.bcv.setOnClickListener {
            val intent = Intent (context, MainActivityInsertar::class.java)
            intent.putExtra("texto" , items.get(position).MATRICULA)
            context.startActivity(intent)
        }

        holder?.itemView?.setOnClickListener(View.OnClickListener { Toast.makeText(context,  items.get(position).MATRICULA, Toast.LENGTH_SHORT).show() })
    }

    fun ImageView.loadUrl(url: String){
        Picasso.with(context).load(url).into(this)
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Mantiene el TextView
    val tvDatosA = view.textViewMarca
    val tvDatosB = view.textViewModelo
    val ivDatos = view.imageView2
    val tvDatosD = view.textViewMatricula
    val tvDatosE = view.textViewDisponibilidad
    val bcv = view.cardView
}
