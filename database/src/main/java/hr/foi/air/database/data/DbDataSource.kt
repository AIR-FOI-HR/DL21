package hr.foi.air.database.data

import android.content.Context
import com.google.gson.Gson
import hr.foi.air.core.data.DataSource
import hr.foi.air.core.data.DataSourceListener
import hr.foi.air.database.DAO
import hr.foi.air.database.MainDatabase
import hr.foi.air.database.entities.Discount
import hr.foi.air.database.entities.Store

class DbDataSource : DataSource {
    private var dao: DAO? = null


    override fun loadData(dataSourceListener: DataSourceListener, context: Context) {
        //Unos podataka u bazu, ako je potrebno
        MockData.mockData(context)

        dao = MainDatabase.getInstance(context).getDao()

        var stores : List<Store>? = dao?.getAllStores()
        var discounts : List<Discount>? = dao?.getAllDiscounts()

        dataSourceListener.onDataLoaded(stores, discounts)
    }
}