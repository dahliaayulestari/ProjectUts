package dahlia.com.example.projectuts.model

import com.google.gson.annotations.SerializedName

data class Dimensions(

    @field:SerializedName("depth")
    val depth: Double,

    @field:SerializedName("width")
    val width: Double,

    @field:SerializedName("height")
    val height: Double
)