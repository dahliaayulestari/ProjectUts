package dahlia.com.example.projectuts.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import dahlia.com.example.projectuts.R
import dahlia.com.example.projectuts.model.ProductsItem
import dahlia.com.example.projectuts.ui.ProductDetailActivity
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class ProductAdapter :
    ListAdapter<ProductsItem, ProductAdapter.ProductViewHolder>(
        DIFF_CALLBACK
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.data_item, parent, false)

        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ProductViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    class ProductViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val ivThumbnail: ImageView =
            itemView.findViewById(R.id.ivThumbnail)

        private val tvTitle: TextView =
            itemView.findViewById(R.id.tvTitle)

        private val tvPrice: TextView =
            itemView.findViewById(R.id.tvPrice)

        private val tvStock: TextView =
            itemView.findViewById(R.id.tvStock)

        private val tvRating: TextView =
            itemView.findViewById(R.id.tvRating)

        fun bind(product: ProductsItem) {
            tvTitle.text = product.title

            val symbols = DecimalFormatSymbols(Locale("id", "ID"))
            val formatter = DecimalFormat("#,##0.##", symbols)

            tvPrice.text =
                "Harga: Rp${formatter.format(product.price)}"

            tvStock.text = "Stok: ${product.stock}"
            tvRating.text = "Rating: ${product.rating}"

            Glide.with(itemView.context)
                .load(product.thumbnail)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_menu_report_image)
                .into(ivThumbnail)

            itemView.setOnClickListener {
                val intent = Intent(
                    itemView.context,
                    ProductDetailActivity::class.java
                )

                intent.putExtra(
                    ProductDetailActivity.EXTRA_PRODUCT_JSON,
                    Gson().toJson(product)
                )

                itemView.context.startActivity(intent)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<ProductsItem>() {

                override fun areItemsTheSame(
                    oldItem: ProductsItem,
                    newItem: ProductsItem
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: ProductsItem,
                    newItem: ProductsItem
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}
