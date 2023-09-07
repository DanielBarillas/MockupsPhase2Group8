package com.example.lab04

/**
 * <h1> Retrofit Pokédex </h1>
 * <h2> Check String / Revisar Cadena </h2>
 *
 * Esta clase nos ayudará a revisar toda la cadena ingresada por el usuario
 *
 * <p> Desarrollo de Plataformas Moviles - Universidad del Valle de Guatemala </p>
 *
 * Creado por:
 * @author Daniel Barillas
 * @version 1.0
 * @since 2023 - Agosto - 06
 *
 **/

class CheckString {

    // --> Atributos
    private var string:String = ""

    // --> Constructor
    constructor(string:String){
        this.string = string
    }

    // --> Métodos
    // Revisar que la caja de texto no reciba más de 50 caracteres
    fun checkChars(): Int {
        return  string.length
    }

    // Validar que no hay numeros en el String
    fun valNumbers(): Boolean{
        var wrongForNumber: Boolean = false

        for(l in string){
            when(l){
                '0','1','2','3','4','5','6','7','8','9' -> wrongForNumber = true
            }
        }

        return wrongForNumber
    }

    // Revisar si hay espacios en blanco en la caja
    fun checkSpace() : Int {
        var count:Int = 0

        for(l in string){
            when(l){
                ' ' -> count++
            }
        }

        return count
    }

    // Método para arreglar String por si el usuario se le ocurrio meter mayusculas
    fun fixString():String{
        var fixedName:String = ""

        for(l in string){
            when {
                l.isLowerCase() -> fixedName += l
                l.isUpperCase() -> fixedName += l.toLowerCase()
                else -> l
            }
        }

        return fixedName
    }
}