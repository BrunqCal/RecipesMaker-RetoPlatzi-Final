package me.brunqcal.RecipesMaker.model

open class Receta(var name: String,list: MutableList<Ingrediente> ){
    var recipeContent: String = ""
    init {
        var aux = 1
        for (ing in list){
            recipeContent += "\n$aux. ${ing.cantidad} ${ing.unidadMedida} de ${ing.name}"
            aux++
        }
    }

    fun read(): String{
    return recipeContent
    }
}