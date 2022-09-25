package com.greenfriends.zeroway.data.model

import com.google.gson.annotations.SerializedName

data class StoreResponse(
    @SerializedName("storeId") val storeId: Long,
    @SerializedName("imageUrl") val imageUrl: String?,
    @SerializedName("name") val name: String,
    @SerializedName("item") val item: String?,
    @SerializedName("addressNew") val addressNew: String,
    @SerializedName("operatingTime") val operatingTime: String?,
    @SerializedName("contact") val contact: String?,
    @SerializedName("siteUrl") val siteUrl: String?,
    @SerializedName("instagram") val instagram: String?
)

data class StorePostDetailResponse(
    @SerializedName("imageUrl") val imageUrl: String?,
    @SerializedName("name") val name: String,
    @SerializedName("item") val item: String?,
    @SerializedName("addressNew") val addressNew: String,
    @SerializedName("operatingTime") val operatingTime: String?,
    @SerializedName("contact") val contact: String?,
    @SerializedName("siteUrl") val siteUrl: String?,
    @SerializedName("instagram") val instagram: String?,
    @SerializedName("description") val description: String?
)