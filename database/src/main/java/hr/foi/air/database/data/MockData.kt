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


        val acmesStore: Store = Store()
        acmesStore.name = "ACMEs store"
        val acmedStore: Store = Store()
        acmedStore.name = "ACMEd store"
        dao?.insertStores(acmedStore)
        dao?.insertStores(acmesStore)

        println("KIA")
        var whatever = dao?.getAllStores()
        println(whatever?.get(0)?.id)
        println(whatever?.get(1)?.id)
        println(whatever?.get(2)?.id)

        val tuna: Discount = Discount()
        tuna.name = "Three for two"
        tuna.discountValue = 33
        tuna.storeId = acmesStore.id
        dao?.insertDiscounts(tuna)

        var whatever2 = dao?.getAllDiscounts()
        println("AIK")
        println(whatever2?.get(0)?.id)
        println(whatever2?.get(0)?.storeId)
        println(whatever2?.get(1)?.id)
        println(whatever2?.get(3)?.id)
        println(whatever2?.get(3)?.storeId)





    }
}