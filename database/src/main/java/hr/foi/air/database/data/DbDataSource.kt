package hr.foi.air.database.data

import android.content.Context
import com.google.gson.Gson
import hr.foi.air.core.data.DataSource
import hr.foi.air.core.data.DataSourceListener
import hr.foi.air.database.DAO
import hr.foi.air.database.MainDatabase

class DbDataSource : DataSource {
    private var dao: DAO? = null

    override fun loadData(dataSourceListener: DataSourceListener, context: Context) {
        dao = MainDatabase.getInstance(context).getDao()

        var stores : List<hr.foi.air.database.entities.Store>? = dao?.getAllStores()
        var discounts : List<hr.foi.air.database.entities.Discount>? = dao?.getAllDiscounts()

        dataSourceListener.onDataLoaded(
            deepCopyList<hr.foi.air.core.entities.Store>(stores),
            deepCopyList<hr.foi.air.core.entities.Discount>(discounts))
    }

    inline fun <reified T>deepCopyList(source: List<Any>?): List<T>
    {
        var dest : ArrayList<T> = ArrayList<T>()
        if (source != null) {
            for (element in source) {
                val JSON = Gson().toJson(element)
                dest.add(Gson().fromJson(JSON, T::class.java))
            }
        }
        return dest.toList()
    }
}