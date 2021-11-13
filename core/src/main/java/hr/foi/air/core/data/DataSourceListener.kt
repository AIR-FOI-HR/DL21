package hr.foi.air.core.data

import hr.foi.air.core.entities.Discount
import hr.foi.air.core.entities.Store

interface DataSourceListener {
    fun onDataLoaded(stores: List<Store>?, discounts: List<Discount>?)
}