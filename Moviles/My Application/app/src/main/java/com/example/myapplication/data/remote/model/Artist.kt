package com.example.myapplication.data.remote.model

import com.google.gson.annotations.SerializedName

data class Artists (

    @SerializedName("external_urls" ) var externalUrls : ExternalUrls? = ExternalUrls(),
    @SerializedName("href"          ) var href         : String?       = null,
    @SerializedName("id"            ) var id           : String?       = null,
    @SerializedName("name"          ) var name         : String         ,
    @SerializedName("type"          ) var type         : String?       = null,
    @SerializedName("uri"           ) var uri          : String?       = null

)