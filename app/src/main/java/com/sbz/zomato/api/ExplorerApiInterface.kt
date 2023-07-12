package com.sbz.zomato.api

import com.sbz.zomato.util.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ExplorerApiInterface {
    val explorerResponse by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ExplorerApi::class.java)
    }
}