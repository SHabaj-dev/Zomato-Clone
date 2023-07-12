package com.sbz.zomato.ui.home

import android.os.Bundle
import android.os.RecoverySystem
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sbz.zomato.R
import com.sbz.zomato.adapters.FoodItemAdapter
import com.sbz.zomato.api.FoodApiInterface
import com.sbz.zomato.databinding.FragmentHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var rv_recomended: RecyclerView
    private lateinit var recommendedAdapter: FoodItemAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_recomended = view.findViewById(R.id.rv_recommended)
        getDataFromApiRecommended()
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
                        recommendedAdapter = FoodItemAdapter(requireContext(), responseBody)
                        rv_recomended.layoutManager =
                            GridLayoutManager(requireContext(), 2, RecyclerView.HORIZONTAL, false)
                        rv_recomended.adapter = recommendedAdapter
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
}