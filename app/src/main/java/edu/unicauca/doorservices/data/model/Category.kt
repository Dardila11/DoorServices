package edu.unicauca.doorservices.data.model

import android.media.Image
import java.util.*

class Category {

    /**
     * docCategoryId is de ID of the document. which is different from
     * the id of the category which is added inside the document
     */
    var docCategoryId: String = ""
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

    constructor(docId: String,  catId: String, catName: String, catDesc: String) {
        this.docCategoryId = docId
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