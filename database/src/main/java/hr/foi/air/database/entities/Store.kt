package hr.foi.air.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stores")
data class Store(
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null,
    var name : String = "",
    var description : String = "",
    var imgUrl: String = "",
    var latitude: Long = 0,
    var longitude: Long = 0
)