package com.example.upbcampus.buildings

class DataModel(name: String, id_: Int, image: Int) {
    var name: String
        internal set
    var id: Int = 0
        internal set
    var image: Int = 0
        internal set

    init {
        this.name = name
        this.id = id_
        this.image = image
    }
}
