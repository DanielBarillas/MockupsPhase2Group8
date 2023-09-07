package com.example.lab04

/**
 * <h1> Retrofit Pokédex </h1>
 * <h2> Main Activity </h2>
 *
 * Clase para inicializar y tener el control del programa
 *
 * <p> Desarrollo de Plataformas Moviles - Universidad del Valle de Guatemala </p>
 *
 * Creado por:
 * @author Daniel Barillas
 * @version 1.0
 * @since 2023 - Agosto - 06
 *
 **/

import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.lab04.pokemon.Pokemon
import com.example.lab04.retrofit.Network
import com.example.lab04.retrofit.Utils
import com.google.gson.Gson
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    // Asosiar los componentes
    var tvName:TextView? = null
    var tvExp:TextView? = null
    var tvHeight:TextView? = null
    var tvWeight:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ********************************************************************
        // Aquí comenzará el código

        // Los campos de interacción
        val btnSearch = findViewById<Button>(R.id.btnSearch)

        val etName = findViewById<EditText>(R.id.etPokemon)

        // Los campos para mostrar información
        tvName = findViewById(R.id.tvName)
        tvExp = findViewById(R.id.tvExp)
        tvHeight = findViewById(R.id.tvHeight)
        tvWeight = findViewById(R.id.tvWeight)

        // Cuando se le hace click al Boton ----------------------------------
        btnSearch.setOnClickListener {

            // De primero vamos a validar el String
            var checkerS:CheckString = CheckString(etName?.text.toString())
            var flags:Int = 0
            var succes:Boolean = false
            var finalPokemon: String = ""

            // De primero revisaremos la cantidad de letras del string
            if(checkerS.checkChars() == 0){
                Toast.makeText(this, "No has ingresado nada", Toast.LENGTH_SHORT).show()
                fullInfo("", "", "", "")
                flags++
            }
            else if(checkerS.checkChars() >= 50){
                Toast.makeText(this, "No hay necesidad de escribir demasiadas letras", Toast.LENGTH_SHORT).show()
                fullInfo("", "", "", "")
                flags++
            }

            // Ahora revisaremos si hay numeros
            if(checkerS.valNumbers()){
                Toast.makeText(this, "No debes de ingresar numeros", Toast.LENGTH_SHORT).show()
                fullInfo("", "", "", "")
                flags++
            }

            // Revisaremos si hay espacios en blanco
            if(checkerS.checkSpace() >= 1){
                Toast.makeText(this, "No debes de ingresar espacios", Toast.LENGTH_SHORT).show()
                fullInfo("", "", "", "")
                flags++
            }

            // Si todos los requisitos fueron cumplidos con exito
            if(flags == 0){
                succes = true
                finalPokemon = checkerS.fixString()
            }

            // Si no hay errores entonces se procederá a realizar la solicitud
            if(succes){
                // Luego vamos a buscar al pokemon
                if(Network.avaliableRed(this)){
                    // Si todo sale en orden se llevará a cabo la solicitud
                    apiRest(Utils.URL_DEF_BASE + finalPokemon)
                }else{
                    Toast.makeText(this, "No hay conexion a internet :(", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    private fun apiRest(url:String){
        val queue = Volley.newRequestQueue(this)

        // Realizar solicitud
        val request = StringRequest(Request.Method.GET, url, {
            response ->
            try {
                Log.d("requestHTTPVolley", response)

                val gson = Gson()
                val pokemon = gson.fromJson(response, Pokemon::class.java)
                Log.d("GSON", pokemon.name)

                Toast.makeText(this, "Encontramos a " + pokemon.name, Toast.LENGTH_SHORT).show()

                fullInfo(
                        "Nombre del Pokémon: "+pokemon.name,
                        "Base Experiencia: " + pokemon.base_experience.toString(),
                        "Altura: " + pokemon.height.toString(),
                        "Peso: " + pokemon.weight.toString()
                )

            }catch (e: Exception){
                Toast.makeText(this, "Acaba de ocurrir un error", Toast.LENGTH_SHORT).show()
            }
        }, Response.ErrorListener {
            Toast.makeText(this, "No se encontro el pokémon :(", Toast.LENGTH_SHORT).show()

            // Para resetear la informacion
            fullInfo("Pokémon no encontrado :(", "", "", "")
        })

        queue.add(request)
    }

    private fun fullInfo(name:String, exp:String, height:String, weight:String){
        tvName?.text = name
        tvExp?.text = exp
        tvHeight?.text = height
        tvWeight?.text = weight
    }

}