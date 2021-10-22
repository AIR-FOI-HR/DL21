package hr.foi.air.database.entities

import androidx.room.*
import java.util.*

@Entity(tableName = "discounts")
data class Discount (
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ForeignKey(entity = Store::class, parentColumns = ["id"], childColumns = ["storeId"])
    @ColumnInfo(index = true)
    var storeId: Int? = null,
    var name: String = "",
    var description: String = "",
    var discountValue: Int = 0,
    var startDate: Date = Date(),
    var endDate: Date = Date()
)