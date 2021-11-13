package hr.foi.air.ws

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.okhttp.OkHttpClient
import hr.foi.air.database.entities.Store
import hr.foi.air.ws.handlers.MyWebserviceHandler
import hr.foi.air.ws.responses.MyWebserviceResponse
import retrofit.*

class MyWebserviceCaller {
    var retrofit: Retrofit? = null
    val baseUrl: String = "http://cortex.foi.hr/mtl/courses/air/"

    constructor() {
        val client: OkHttpClient = OkHttpClient()

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun getAllStores(method: String, dataArrivedHandler: MyWebserviceHandler)
    {
        val serviceCaller: MyWebservice? = retrofit?.create(MyWebservice::class.java)
        var call: Call<MyWebserviceResponse>? = null
        if (serviceCaller != null) {
            call = serviceCaller.getAllStores(method)
        }

        if(call != null){
            call.enqueue(object: Callback<MyWebserviceResponse> {
                override fun onFailure(t: Throwable?) {
                    t?.printStackTrace()
                }

                override fun onResponse(
                    response: Response<MyWebserviceResponse>?,
                    retrofit: Retrofit?
                ) {
                    try{
                        if (response != null) {
                            if(response.isSuccess()){
                                println("Got stores... Processing...")
                                val gson : Gson = Gson()
                                val storeItems: Array<Store>? = gson.fromJson(response.body().items, Array<Store>::class.java)

                                if (storeItems != null) {
                                    //data obtained stend it to handler
                                    dataArrivedHandler.onDataArrived<Store>(storeItems.toList(), true, response.body().timeStamp)
                                }
                            }
                        }
                    }catch (ex: Exception){
                        ex.printStackTrace()
                    }
                }
            })
        }
    }
}