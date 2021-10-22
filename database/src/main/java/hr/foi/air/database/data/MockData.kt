package hr.foi.air.database.data

import android.content.Context
import hr.foi.air.database.DAO
import hr.foi.air.database.MainDatabase
import hr.foi.air.database.entities.Discount
import hr.foi.air.database.entities.Store

object MockData {
    private var dao:DAO? = null

    fun mockData(context: Context){
        dao = MainDatabase.getInstance(context).getDao()

        //check if data already exists
        val stores: List<Store> = dao!!.getAllStores()
        if (stores != null) {
            if (stores.isEmpty()) {

                val acmeStore: Store = Store()
                acmeStore.name = "ACME store"
                acmeStore.id = dao?.insertStores(acmeStore)?.get(0)?.toInt()

                val apples: Discount = Discount()
                apples.name = "Apples of 10%"
                apples.discountValue = 10
                apples.storeId = acmeStore.id

                val tuna: Discount = Discount()
                tuna.name = "Three for two"
                tuna.discountValue = 33
                tuna.storeId = acmeStore.id

                dao?.insertDiscounts(apples)
                dao?.insertDiscounts(tuna)
            }
        }
    }
}