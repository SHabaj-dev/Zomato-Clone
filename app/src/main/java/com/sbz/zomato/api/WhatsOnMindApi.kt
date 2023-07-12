package com.sbz.zomato.api

import com.sbz.zomato.models.WhatsOnMindModel
import retrofit2.Response
import retrofit2.http.GET

interface WhatsOnMindApi {
    @GET("whats_on_your_mind.json")
    suspend fun getWhatsOnMind(): Response<List<WhatsOnMindModel>>
}