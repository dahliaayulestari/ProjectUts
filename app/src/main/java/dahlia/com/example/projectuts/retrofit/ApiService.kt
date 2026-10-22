
package dahlia.com.example.projectuts.retrofit

import dahlia.com.example.projectuts.response.ProductResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("products?limit=25&select=id,title,description,category,price,stock,availabilityStatus,tags,dimensions,reviews,rating,images,thumbnail")
    fun getProducts(): Call<ProductResponse>
}
