package dahlia.com.example.projectuts.model

import com.google.gson.annotations.SerializedName

data class ProductsItem(

    @field:SerializedName("images")
    val images: List<String>,

    @field:SerializedName("thumbnail")
    val thumbnail: String,

    @field:SerializedName("rating")
    val rating: Double,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("tags")
    val tags: List<String>,

    @field:SerializedName("reviews")
    val reviews: List<ReviewsItem>,

    @field:SerializedName("price")
    val price: Double,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("availabilityStatus")
    val availabilityStatus: String,

    @field:SerializedName("category")
    val category: String,

    @field:SerializedName("stock")
    val stock: Int,

    @field:SerializedName("dimensions")
    val dimensions: Dimensions
)
