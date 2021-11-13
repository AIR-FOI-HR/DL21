package hr.foi.air.core.entities

data class Store(
    var id : Int? = null,
    var name : String = "",
    var description : String = "",
    var imgUrl: String = "",
    var latitude: Long = 0,
    var longitude: Long = 0
)