package com.sbz.zomato.api

import com.sbz.zomato.models.FoodModel
import retrofit2.Response
import retrofit2.http.GET

interface FoodApi {

    @GET("data.json")
    suspend fun getFoodItems(): Response<List<FoodModel>>
}