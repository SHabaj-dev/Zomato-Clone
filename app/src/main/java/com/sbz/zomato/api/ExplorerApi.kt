package com.sbz.zomato.api

import com.sbz.zomato.models.ExplorerModel
import retrofit2.Response
import retrofit2.http.GET

interface ExplorerApi {
    @GET("explore.json")
    suspend fun getExplorer(): Response<List<ExplorerModel>>
}