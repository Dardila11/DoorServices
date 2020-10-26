package edu.unicauca.doorservices.data.model

class Category {

    var categoryId: String  = ""
    var categoryName: String = ""


    constructor()

    constructor(catId: String, catName: String) {
        this.categoryId = catId
        this.categoryName = catName
    }



}