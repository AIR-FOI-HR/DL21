package hr.foi.air.database

import androidx.room.*
import hr.foi.air.database.entities.Discount
import hr.foi.air.database.entities.Store

@Dao
interface DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStores(vararg stores: Store) : List<Long>
    @Update fun updateStores(vararg stores: Store)
    @Delete fun deleteStores(vararg stores: Store)

    @Insert fun insertDiscounts(vararg discounts: Discount)
    @Update fun updateDiscounts(vararg discounts: Discount)
    @Delete fun deleteDiscounts(vararg discounts: Discount)

    @Query("SELECT * FROM stores")
    fun getAllStores(): List<Store>

    @Query("SELECT * FROM discounts")
    fun getAllDiscounts(): List<Discount>

    @Query("SELECT * FROM stores WHERE name LIKE :name")
    fun getAllStoresByName(name: String): List<Store>

    @Query("SELECT * FROM discounts WHERE storeId = :storeId")
    fun getAllDiscountsByStore(storeId: Int): List<Discount>
}