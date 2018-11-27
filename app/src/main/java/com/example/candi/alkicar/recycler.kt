package com.example.candi.alkicar

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_recycler.*
import java.io.IOException
import java.net.URL


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
  * Una simple subclase [Fragment].
  * Las actividades que contienen este fragmento deben implementar el
  * [recycler.OnFragmentInteractionListener] interfaz
  * Para manejar eventos de interacción.
  * Utilice el método de fábrica [recycler.newInstance] para
  * Crea una instancia de este fragmento.
  *
  */
class recycler : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    private var datos: CocheArray = CocheArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        // Inflar el diseño de este fragment
        return inflater.inflate(R.layout.fragment_recycler, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        URLJsonObjeto()
        val asdf = datos.getArray()
        recyclerView.layoutManager = LinearLayoutManager(context)
        //recyclerView.layoutManager = GridLayoutManager(context,1)
        recyclerView.adapter=DatosAdapter(asdf,context!!)
    }

    private fun leerUrl(urlString:String): String{

        val response = try {
            URL(urlString)
                .openStream()
                .bufferedReader()
                .use { it.readText() }
        } catch (e: IOException) {
            "Error with ${e.message}."
        }

        return response
    }
    fun URLJsonObjeto() {
       val gson = Gson()
        try {
            val json = leerUrl("http://iesayala.ddns.net/candido/a.php")

            val coche = gson.fromJson(json, CocheArray::class.java)

            //Donde se muestra el Json
            for (item in coche.Coches!!.iterator()) {
                if ( item.DISPONIBILIDAD!="NO"){
                    Log.d("RESULTADO", item.MARCA)
                    var reg: Coche = Coche ("" + item.MATRICULA, "" + item.MARCA, "" + item.MODELO,"" + item.DISPONIBILIDAD ,"" + item.IMAGEN)
                    datos.addCoche(reg)
                }
            }
        }
        catch (e: Exception){
            Log.d("RESULTADO", "error")
        }


    }
    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * Esta interfaz debe ser implementada por actividades que contengan esto.
     * fragment para permitir que una interacción en este fragment sea comunicada.
     * a la activity y potencialmente a otros fragments contenidos en esa
     * activity.
     *
     *
     * Vea la lección de capacitación de Android [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * para más información.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }
    companion object {
        /**
         * Utilice este método de fábrica para crear una nueva instancia de
         * este fragment utilizando los parámetros proporcionados.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return Una nueva instancia de fragment recycler.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            recycler().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
