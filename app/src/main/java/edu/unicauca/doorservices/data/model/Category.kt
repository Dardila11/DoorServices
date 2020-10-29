package edu.unicauca.doorservices.data.model

import android.media.Image
import java.util.*

class Category {

    var categoryId: String  = ""
    var categoryName: String = ""
    var categoryDescription: String = ""
    lateinit var categoryImage: Image

    constructor()

    constructor(catId: String, catName: String, catDesc: String) {
        this.categoryId = catId
        this.categoryName = catName
        this.categoryDescription = catDesc
    }

    constructor(catId: String, catName: String, catDesc: String, catImage: Image){
        this.categoryId = catId
        this.categoryName = catName
        this.categoryDescription = catDesc
        this.categoryImage = catImage
    }
}

class CategoryImage {
    var path: String = ""
    lateinit var base64: Base64

    constructor()

    constructor(base64: Base64, path: String){
        this.base64 = base64
        this.path = path
    }
}