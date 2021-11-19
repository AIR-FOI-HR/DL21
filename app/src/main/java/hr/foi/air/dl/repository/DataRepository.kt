package hr.foi.air.dl.repository

import android.content.Context
import hr.foi.air.core.data.DataSource
import hr.foi.air.core.data.DataSourceListener
import hr.foi.air.core.entities.Discount
import hr.foi.air.core.entities.Store
import hr.foi.air.core.helpers.InternetCheck
import hr.foi.air.database.data.DbDataSource
import hr.foi.air.ws.WsDataSource

class DataRepository {

    fun loadData(context : Context, listener : LoadDataListener)
    {
        //check if device is online
        var internetCheck = InternetCheck()
        var isOnline = internetCheck.isOnline(context)

        //use proper module to obtain data
        var dataSource : DataSource?
        if (isOnline)
            dataSource = WsDataSource()
        else
            dataSource = DbDataSource()

        dataSource.loadData(
            object : DataSourceListener {
                override fun onDataLoaded(stores: List<Store>?, discounts: List<Discount>?) {
                    listener.onDataLoaded(stores, discounts)

                    //TODO
                    //provjeriti izvor i po potrebi napravit cache podataka
                }
            },
            context
        )
    }
}