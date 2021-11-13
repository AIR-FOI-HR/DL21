package hr.foi.air.ws

import hr.foi.air.ws.responses.MyWebserviceResponse
import retrofit.Call
import retrofit.http.Field
import retrofit.http.FormUrlEncoded
import retrofit.http.POST

interface MyWebservice {
    @FormUrlEncoded
    @POST("stores.php")
    fun getAllStores(@Field("method") method: String) : Call<MyWebserviceResponse>

    @FormUrlEncoded
    @POST("discounts.php")
    fun getAllDiscounts(@Field("method") method: String) : Call<MyWebserviceResponse>
}