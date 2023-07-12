package com.sbz.zomato.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sbz.zomato.R
import com.sbz.zomato.adapters.ExplorerAdapter
import com.sbz.zomato.adapters.FoodItemAdapter
import com.sbz.zomato.adapters.WhatsOnMindAdapter
import com.sbz.zomato.api.ExplorerApiInterface
import com.sbz.zomato.api.FoodApiInterface
import com.sbz.zomato.api.WhatsOnMindInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var rvRecomended: RecyclerView
    private lateinit var rvExplorer: RecyclerView
    private lateinit var rvHotDeals: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvRecomended = view.findViewById(R.id.rv_recommended)
        rvExplorer = view.findViewById(R.id.rv_explore)
        rvHotDeals = view.findViewById(R.id.rv_hotDeals)
        getDataFromApiRecommended()
        getDataFromApiExplorer()
        getDataForWhatsOnMind()
    }

    private fun getDataFromApiRecommended() {
        lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    FoodApiInterface.apiResponse.getFoodItems()
                }

                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        val recommendedAdapter = FoodItemAdapter(requireContext(), responseBody)
                        rvRecomended.layoutManager =
                            GridLayoutManager(requireContext(), 2, RecyclerView.HORIZONTAL, false)
                        rvRecomended.adapter = recommendedAdapter
                    }
                } else {
                    // Handle unsuccessful response
                    Log.e("SHABAJ_ERROR", "Request failed with code: ${response.code()}")
                }
            } catch (e: IOException) {
                // Handle IO exception
                e.printStackTrace()
            } catch (e: HttpException) {
                // Handle HTTP exception
                e.printStackTrace()
            }
        }
    }

    private fun getDataFromApiExplorer() {
        lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    ExplorerApiInterface.explorerResponse.getExplorer()
                }

                if (response.isSuccessful && response.body() != null) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        Log.d("THIS IS RESPONSE", responseBody.toString())
                        val adapter = ExplorerAdapter(requireContext(), responseBody)
                        rvExplorer.layoutManager =
                            GridLayoutManager(requireContext(), 1, RecyclerView.HORIZONTAL, false)
                        rvExplorer.adapter = adapter

                    }
                }

            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: HttpException) {
                e.printStackTrace()
            }
        }
    }

    private fun getDataForWhatsOnMind() {
        lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    WhatsOnMindInterface.whatsOnMindApiResponse.getWhatsOnMind()
                }

                if (response.isSuccessful && response.body() != null) {
                    val responseBody = response.body()
                    if (responseBody != null) {
//                        Toast.makeText(requireContext(), "we are here", Toast.LENGTH_SHORT).show()
                        val adapter = WhatsOnMindAdapter(requireContext(), responseBody)
                        rvHotDeals.layoutManager = LinearLayoutManager(requireContext())
                        rvHotDeals.adapter = adapter
                    }
                }

            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: HttpException) {
                e.printStackTrace()
            }
        }
    }
}