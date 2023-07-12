package com.sbz.zomato.models

data class WhatsOnMindModel(
    val imageUrl: String,
    val itemName: String,
    val restaurantName: String,
    val totalOffPercentage: String,
    val price: Double,
    val timeToDeliver: String,
)