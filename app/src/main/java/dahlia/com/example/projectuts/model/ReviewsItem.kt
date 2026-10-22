package dahlia.com.example.projectuts.model

import com.google.gson.annotations.SerializedName

data class ReviewsItem(

    @field:SerializedName("date")
    val date: String,

    @field:SerializedName("reviewerName")
    val reviewerName: String,

    @field:SerializedName("reviewerEmail")
    val reviewerEmail: String,

    @field:SerializedName("rating")
    val rating: Int,

    @field:SerializedName("comment")
    val comment: String
)