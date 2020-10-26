package edu.unicauca.doorservices.data.model

class Category {

    var categoryId: String  = ""
    var categoryName: String = ""
    var categoryDescription: String = ""


    constructor()

    constructor(catId: String, catName: String, catDesc: String) {
        this.categoryId = catId
        this.categoryName = catName
        this.categoryDescription = catDesc
    }



}