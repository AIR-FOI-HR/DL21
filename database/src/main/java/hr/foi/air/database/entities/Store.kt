package hr.foi.air.database.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import hr.foi.air.core.entities.Store

@Entity(tableName = "stores", primaryKeys = ["id"])
class Store(): Store()
{
    @PrimaryKey(autoGenerate = true)
    @Ignore override var id : Int? = null;
}