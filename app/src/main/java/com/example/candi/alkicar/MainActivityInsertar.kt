package com.example.candi.alkicar

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main_insertar.*
import java.io.IOException
import java.net.URL

class MainActivityInsertar : AppCompatActivity() {

    var matricula : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_insertar)

        val bundle:Bundle?=intent.extras
        //si es distinto de nulo cargo el mensaje
        if (bundle!=null) {
            matricula = bundle.getString("texto")
        }

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
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

    fun cancelar (v: View){
        finish()
    }

    fun modificar (v: View){
        val url = "http://iesayala.ddns.net/candido/c.php/?matricula=" + matricula
        Toast.makeText(this, "El coche con matricula: " + matricula + " ha sido reservado", Toast.LENGTH_SHORT).show()
        leerUrl(url)
        val url2 = "http://iesayala.ddns.net/candido/d.php/?dni=" + editText5.text.toString() +
                "&nombre=" + editText.text.toString() + "&apellido1=" + editText2.text.toString() +
                "&apellido2=" + editText3.text.toString() + "&email=" + editText4.text.toString() + "&telefono=" + editText6.text.toString() +
                "&matricula=" + matricula
        Toast.makeText(this, "Datos Insertados", Toast.LENGTH_SHORT).show()
        leerUrl(url2)
        finish()
    }

    fun reservar (v: View){

    }

}
