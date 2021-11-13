package hr.foi.air.ws

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.okhttp.OkHttpClient
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

    fun getAllStores(method: String)
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
                    t?.printStackTrace()
                }
            })
        }
    }
}