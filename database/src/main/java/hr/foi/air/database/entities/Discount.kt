package hr.foi.air.database.entities

import androidx.room.*
import hr.foi.air.core.entities.Discount
import hr.foi.air.database.converters.DateConverter
import java.util.*

@Entity(tableName = "discounts", primaryKeys = ["id"])
@TypeConverters(DateConverter::class)
class Discount (): Discount(){
    @PrimaryKey(autoGenerate = true)
    @Ignore override var id: Int? = null;
    @ForeignKey(entity = Store::class, parentColumns = ["id"], childColumns = ["storeId"])
    @ColumnInfo(index = true)
    @Ignore override var storeId: Int? = null;
}