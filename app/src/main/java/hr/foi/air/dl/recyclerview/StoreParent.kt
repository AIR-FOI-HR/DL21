package hr.foi.air.dl.recyclerview

import com.bignerdranch.expandablerecyclerview.model.Parent
import hr.foi.air.core.entities.Discount
import hr.foi.air.core.entities.Store

class StoreParent(store: Store, allDiscounts: List<Discount>) : Parent<Discount>,
    Store(store.id, store.name, store.description, store.imgUrl, store.latitude, store.longitude) {

    var myDiscounts = allDiscounts.filter { d -> d.storeId == this.id }

    override fun getChildList(): List<Discount> {
        return myDiscounts
    }

    override fun isInitiallyExpanded(): Boolean {
        return false
    }
}