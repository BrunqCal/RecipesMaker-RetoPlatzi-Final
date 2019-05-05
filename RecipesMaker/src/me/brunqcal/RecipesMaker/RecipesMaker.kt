package me.brunqcal.RecipesMaker

import me.brunqcal.RecipesMaker.model.*
import java.lang.NumberFormatException

var exit = 1
var selectedIngsList: MutableList<Ingrediente> = mutableListOf()
var recetasList: MutableList<Receta> = mutableListOf()
var aguaList: MutableList<Ingrediente> = mutableListOf()
var lacteosList: MutableList<Ingrediente> = mutableListOf()
var carnesList: MutableList<Ingrediente> = mutableListOf()
var verdurasList: MutableList<Ingrediente> = mutableListOf()
var frutasList: MutableList<Ingrediente> = mutableListOf()
var cerealList: MutableList<Ingrediente> = mutableListOf()
var huevosList: MutableList<Ingrediente> = mutableListOf()
var aceiteList: MutableList<Ingrediente> = mutableListOf()

fun main(){
    showMenu()
}
fun showMenu(){
    do {
        println("\n:: Bienvenido a Recipe Maker ::\n\n")
        println("Seleciona la opción deseada:")
        println("1. Hacer una receta.")
        println("2. Ver mis recetas.")
        println("3. Salir")
        var response: Int?
        try {
            response = readLine()?.toInt() ?: 3
        }catch (nfe: NumberFormatException){
            response = 3
        }
        when(response){
            1 -> makeRecipe()
            2 -> viewRecipe()
            3 -> exit = 0
        }
        println("Tu respuesta fue: $response")
    }while (exit.equals(1))
}

fun makeRecipe(){
    println(":: CREACIÓN DE RECETA ::")
    var finished = false
    var userRes: Int? = -1
    do {
        println("Ingrese la categoría:")
        println("1. Agua")
        println("2. Leche")
        println("3. Carne")
        println("4. Verduras")
        println("5. Frutas")
        println("6. Cereal")
        println("7, Huevos")
        println("8. Aceite")
        println("0. Salir")
        try {
            userRes = readLine()?.toInt()
        }catch (nfe: NumberFormatException){
            println("Ocurrió un fallo obteniendo su respuesta, intentelo nuevamente")
        }
        if(userRes!!.equals(0)){
            break
        }
        when(userRes){
            1 -> AguaCategory("Agua", aguaList, selectedIngsList).ingredientList()
            2 -> LecheCategory("Lacteos", lacteosList, selectedIngsList).ingredientList()
            3 -> CarneCategory("Carne", carnesList, selectedIngsList).ingredientList()
            4 -> VerdurasCategory("Verduras", verdurasList, selectedIngsList).ingredientList()
            5 -> FrutasCategory("Frutas", frutasList, selectedIngsList).ingredientList()
            6 -> CerealCategory("Cereal", frutasList, selectedIngsList).ingredientList()
            7 -> HuevosCategory("Huevos", huevosList, selectedIngsList).ingredientList()
            8 -> AceiteCategory("Aceite", aceiteList, selectedIngsList).ingredientList()
        }
    }while (!finished)
    println("¿Qué nombre le quiere dar a su receta?")
    var nameOfRecipe: String? = readLine().toString()
    recetasList.add(Receta(nameOfRecipe!!, selectedIngsList))
    println("Receta creada satisfactoriamente")
    selectedIngsList.clear()
}
fun viewRecipe(){
    if(recetasList.isEmpty()){
        println("Ingresa nuevamente cuando hayas creado alguna receta")
    }else {
        if(recetasList.size > 1){
            println("Selecciona la receta que gustes leer")
            var aux = 1
            for (recipe in recetasList){
                println("$aux. ${recipe.name}")
                aux++
            }
            var userResponse: Int?
            try {
                userResponse = readLine()?.toInt()
            }catch (nfe: NumberFormatException){
                userResponse = 1
            }
            if(userResponse !!>= recetasList.size || userResponse !!<= recetasList.size.minus(recetasList.size.plus(1))){
                println("Has ingresado una receta que no existe, te redirigiremos a la última receta disponible")
                println("${recetasList.last().name}")
                println("${recetasList.last().read()}")
            }else {
                println("La receta seleccionada es: ${recetasList.get(userResponse-1).name}")
                println("${recetasList.get(userResponse-1).read()}")
            }
        }else {
            println("Como solo haz creado una receta, te redigiremos a ella.")
            println("${recetasList.first().name}")
            println("${recetasList.first().read()}")
        }
    }
}