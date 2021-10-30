package hr.foi.air.database.data

import android.content.Context
import hr.foi.air.database.DAO
import hr.foi.air.database.MainDatabase

class DataRepository(context: Context) {
    private var dao: DAO? = null

    init {
        dao = MainDatabase.getInstance(context).getDao()
    }

    fun getDiscountNames() : List<String>?
    {
        return dao?.getDiscountNames()
    }
}