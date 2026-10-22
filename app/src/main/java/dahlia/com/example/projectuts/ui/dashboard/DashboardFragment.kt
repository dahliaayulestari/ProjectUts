package dahlia.com.example.projectuts.ui.dashboard

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dahlia.com.example.projectuts.R
import dahlia.com.example.projectuts.adapter.ProductAdapter
import dahlia.com.example.projectuts.retrofit.ApiConfig
import dahlia.com.example.projectuts.response.ProductResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private lateinit var productAdapter: ProductAdapter
    private lateinit var rvProducts: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        rvProducts = view.findViewById(R.id.rvProducts)
        progressBar = view.findViewById(R.id.progressBar)

        // Menyiapkan adapter dan daftar produk
        productAdapter = ProductAdapter()

        rvProducts.layoutManager = LinearLayoutManager(requireContext())
        rvProducts.adapter = productAdapter

        // Mengambil data produk dari API
        loadProducts()
    }

    private fun loadProducts() {
        progressBar.visibility = View.VISIBLE

        ApiConfig.getApiService()
            .getProducts()
            .enqueue(object : Callback<ProductResponse> {

                override fun onResponse(
                    call: Call<ProductResponse>,
                    response: Response<ProductResponse>
                ) {
                    if (!isAdded) return

                    progressBar.visibility = View.GONE

                    if (response.isSuccessful) {
                        val products = response.body()?.products.orEmpty()
                        productAdapter.submitList(products)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Gagal mengambil data: ${response.code()}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onFailure(
                    call: Call<ProductResponse>,
                    t: Throwable
                ) {
                    if (!isAdded) return

                    progressBar.visibility = View.GONE

                    Toast.makeText(
                        requireContext(),
                        "Kesalahan koneksi: ${t.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
    }
}
