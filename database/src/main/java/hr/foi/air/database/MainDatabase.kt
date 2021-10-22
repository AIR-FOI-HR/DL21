package hr.foi.air.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hr.foi.air.database.entities.Discount
import hr.foi.air.database.entities.Store
import hr.foi.air.database.views.DiscountDetails

@Database(version = 1, entities = [Store::class, Discount::class], views = [DiscountDetails::class], exportSchema = false)
abstract class MainDatabase : RoomDatabase() {
    abstract fun getDao(): DAO

    companion object
    {
        private var instance : MainDatabase? = null

        fun getInstance(context : Context) : MainDatabase
        {
            if (instance == null)
            {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDatabase::class.java, "main.db"
                ).allowMainThreadQueries().build()
            }

            return instance as MainDatabase
        }
    }
}