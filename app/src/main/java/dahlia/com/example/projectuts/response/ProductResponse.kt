package dahlia.com.example.projectuts.response

import com.google.gson.annotations.SerializedName
import dahlia.com.example.projectuts.model.ProductsItem

data class ProductResponse(

	@field:SerializedName("total")
	val total: Int,

	@field:SerializedName("limit")
	val limit: Int,

	@field:SerializedName("skip")
	val skip: Int,

	@field:SerializedName("products")
	val products: List<ProductsItem>
)





