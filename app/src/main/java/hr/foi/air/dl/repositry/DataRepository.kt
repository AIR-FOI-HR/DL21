package hr.foi.air.dl.repositry

import android.content.Context
import hr.foi.air.core.data.DataSource
import hr.foi.air.core.data.DataSourceListener
import hr.foi.air.core.entities.Discount
import hr.foi.air.core.entities.Store
import hr.foi.air.core.helpers.InternetCheck
import hr.foi.air.database.data.DbDataSource
import hr.foi.air.ws.WsDataSource

class DataRepository {

    fun getDiscountNames(context : Context) : List<String>?
    {
        //check if device is online
        var internetCheck = InternetCheck()
        var isOnline = internetCheck.isOnline(context)

        //use proper module to obtain data
        var dataSource : DataSource? = null
        if (isOnline)
            dataSource = WsDataSource()
        else
            dataSource = DbDataSource()

        dataSource.loadData(
            object : DataSourceListener {
                override fun onDataLoaded(stores: List<Store>?, discounts: List<Discount>?) {
                    TODO("Not yet implemented")
                }
            },
            context
        )

        return null
    }
}