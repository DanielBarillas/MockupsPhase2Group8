package com.example.lab04.pokemon

/**
 * <h1> Retrofit Pokédex </h1>
 * <h2> Pokemon </h2>
 *
 * Esta clase representará a un Pokemon
 *
 * <p> Desarrollo de Plataformas Moviles - Universidad del Valle de Guatemala </p>
 *
 * Creado por:
 * @author Daniel Barillas
 * @version 1.0
 * @since 2023 - Agosto - 06
 *
 **/

class Pokemon {

    // --> Atributos
    var name:String = ""
    var base_experience:Int = 0
    var height:Int = 0
    var weight:Int = 0

    // --> Constructor
    constructor(name:String, base_experience:Int, height:Int, weight:Int){
        this.name = name
        this.base_experience = base_experience
        this.height = height
        this.weight = weight
    }
}