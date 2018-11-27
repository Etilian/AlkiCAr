package com.example.candi.alkicar

import android.net.Uri
import android.os.Bundle
import android.os.StrictMode
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.security.KeyStore


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, recycler.OnFragmentInteractionListener, recycler2Fragment.OnFragmentInteractionListener {

    var admin = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //Variables del Login que hacen referencia a la vista
        var btn_cliente = findViewById(R.id.btn_cliente) as Button
        var btn_administrador = findViewById(R.id.btn_administrador) as Button

        //Código para limpiar los campos en caso de cancelar
        btn_cliente.setOnClickListener {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
            )
            drawer_layout.addDrawerListener(toggle)
            toggle.syncState()

            nav_view.setNavigationItemSelectedListener(this)
            Toast.makeText(this@MainActivity, "Elija un coche en el menu", Toast.LENGTH_LONG).show()
        }

        //Código para aceptar el login
        btn_administrador.setOnClickListener {
            val contrasenia = editTextcontrasenia.text.toString()
            if ( contrasenia.equals("admin1234") ){
                val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
                StrictMode.setThreadPolicy(policy)

                val toggle = ActionBarDrawerToggle(
                    this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
                )
                drawer_layout.addDrawerListener(toggle)
                toggle.syncState()

                nav_view.setNavigationItemSelectedListener(this)

                admin = true

                Toast.makeText(this@MainActivity, "Todas las opciones desbloqueadas", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@MainActivity, "Contaseña incorrecta", Toast.LENGTH_LONG).show()
            }
        }


    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        if ( admin == true ) {
            when (item.itemId) {
                R.id.nav_coches -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.content_main, recycler.newInstance("a","b"), "rageComicList")
                        .commit()
                }
                R.id.nav_clientes -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.content_main, recycler2Fragment.newInstance("a","b"), "rageComicList")
                        .commit()
                }
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            return true
        } else {
            when (item.itemId) {
                R.id.nav_coches -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.content_main, recycler.newInstance("a","b"), "rageComicList")
                        .commit()
                }
                R.id.nav_clientes -> {
                    Toast.makeText(this@MainActivity, "Solo el administrador puede ver los clientes", Toast.LENGTH_LONG).show()
                }
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            return true
        }
    }

    //Sino se sobre escribe esta función, da error al meter el Fragment.
    override fun onFragmentInteraction(uri: Uri) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
