package dahlia.com.example.projectuts.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import dahlia.com.example.projectuts.R
import dahlia.com.example.projectuts.model.ProductsItem
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class ProductDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PRODUCT_JSON = "extra_product_json"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val productJson = intent.getStringExtra(EXTRA_PRODUCT_JSON)

        if (productJson.isNullOrBlank()) {
            finish()
            return
        }

        val product = Gson().fromJson(
            productJson,
            ProductsItem::class.java
        )

        val ivProductImage =
            findViewById<ImageView>(R.id.ivProductImage)
        val tvDetailTitle =
            findViewById<TextView>(R.id.tvDetailTitle)
        val tvDetailPrice =
            findViewById<TextView>(R.id.tvDetailPrice)
        val tvDetailDescription =
            findViewById<TextView>(R.id.tvDetailDescription)
        val tvDetailId =
            findViewById<TextView>(R.id.tvDetailId)
        val tvDetailCategory =
            findViewById<TextView>(R.id.tvDetailCategory)
        val tvDetailStock =
            findViewById<TextView>(R.id.tvDetailStock)
        val tvDetailAvailability =
            findViewById<TextView>(R.id.tvDetailAvailability)
        val tvDetailRating =
            findViewById<TextView>(R.id.tvDetailRating)
        val tvDetailTags =
            findViewById<TextView>(R.id.tvDetailTags)
        val tvDetailDimensions =
            findViewById<TextView>(R.id.tvDetailDimensions)
        val tvReview =
            findViewById<TextView>(R.id.tvReview)
        val tvSeeAllReviews =
            findViewById<TextView>(R.id.tvSeeAllReviews)

        Glide.with(this)
            .load(product.images.firstOrNull() ?: product.thumbnail)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_menu_report_image)
            .into(ivProductImage)

        val symbols = DecimalFormatSymbols(Locale("id", "ID"))
        val formatter = DecimalFormat("#,##0.##", symbols)

        tvDetailTitle.text = product.title
        tvDetailPrice.text = "Harga: Rp${formatter.format(product.price)}"
        tvDetailDescription.text = "Deskripsi: ${product.description}"
        tvDetailId.text = "ID Produk: ${product.id}"
        tvDetailCategory.text = "Kategori: ${product.category}"
        tvDetailStock.text = "Stok: ${product.stock}"
        tvDetailAvailability.text =
            "Status: ${product.availabilityStatus}"
        tvDetailRating.text = "Rating: ${product.rating}"
        tvDetailTags.text = "Tags: ${product.tags.joinToString(", ")}"

        val dimensions = product.dimensions
        tvDetailDimensions.text =
            "Dimensi: Lebar ${dimensions.width}, " +
                    "Tinggi ${dimensions.height}, " +
                    "Kedalaman ${dimensions.depth}"

        val firstReview = product.reviews.firstOrNull()

        tvReview.text = if (firstReview != null) {
            "Reviewer: ${firstReview.reviewerName}\n" +
                    "Rating: ${firstReview.rating}\n" +
                    "Komentar: ${firstReview.comment}\n" +
                    "Tanggal: ${firstReview.date}"
        } else {
            "Belum ada review untuk produk ini."
        }

        tvSeeAllReviews.visibility =
            if (product.reviews.size > 1) View.VISIBLE else View.GONE

        tvSeeAllReviews.setOnClickListener {
            val allReviews = product.reviews.joinToString(
                separator = "\n\n"
            ) { review ->
                "Reviewer: ${review.reviewerName}\n" +
                        "Rating: ${review.rating}\n" +
                        "Komentar: ${review.comment}\n" +
                        "Tanggal: ${review.date}"
            }

            androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Semua Review")
                .setMessage(allReviews.ifBlank { "Belum ada review." })
                .setPositiveButton("Tutup", null)
                .show()
        }
    }
}
