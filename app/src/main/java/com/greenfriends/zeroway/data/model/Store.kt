package com.greenfriends.zeroway.data.model

import com.google.gson.annotations.SerializedName

data class StoreResponse(
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("name") val name: String,
    @SerializedName("item") val item: String,
    @SerializedName("scoreAvg") val scoreAvg: Double,
    @SerializedName("addressNew") val addressNew: String,
    @SerializedName("operatingTime") val operatingTime: String,
    @SerializedName("contact") val contact: String,
    @SerializedName("siteUrl") val siteUrl: String,
    @SerializedName("instagram") val instagram: String
)