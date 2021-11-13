package hr.foi.air.ws

import android.content.Context
import hr.foi.air.core.data.DataSource
import hr.foi.air.core.data.DataSourceListener
import hr.foi.air.core.entities.Discount
import hr.foi.air.core.entities.Store
import hr.foi.air.ws.handlers.MyWebserviceHandler

class WsDataSource : DataSource {
    private var listener: DataSourceListener? = null
    private var stores: List<Store>? = null
    private var discounts: List<Discount>? = null

    private var storesArrived: Boolean = false
    private var discountsArrived: Boolean = false

    override fun loadData(dataSourceListener: DataSourceListener, context: Context) {

        this.listener = dataSourceListener

        val storeCaller: MyWebserviceCaller = MyWebserviceCaller()
        val discountCaller: MyWebserviceCaller = MyWebserviceCaller()

        storeCaller.getAllStores("getAll", storeHandler)
        discountCaller.getAllDiscounts("getAll", discountHandler)
    }

    @Suppress("UNCHECKED_CAST")
    private val storeHandler: MyWebserviceHandler = object : MyWebserviceHandler{
        override fun <T> onDataArrived(result: List<T>, ok: Boolean, timeStamp: Long?) {
            if(ok){
                stores = result as List<Store>
            }
            storesArrived = true
            checkDataArrival()
        }

    }

    @Suppress("UNCHECKED_CAST")
    private val discountHandler: MyWebserviceHandler = object : MyWebserviceHandler{
        override fun <T> onDataArrived(result: List<T>, ok: Boolean, timeStamp: Long?) {
            if(ok){
                discounts = result as List<Discount>
            }
            discountsArrived = true
            checkDataArrival()
        }

    }

    private fun checkDataArrival(){
        if(storesArrived && discountsArrived){
            listener?.onDataLoaded(stores, discounts)
        }
    }
}