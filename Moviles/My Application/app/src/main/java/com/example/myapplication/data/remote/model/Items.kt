package com.example.myapplication.data.remote.model

import com.google.gson.annotations.SerializedName

data class Items (

    @SerializedName("artists"           ) var artists          : ArrayList<Artists> = arrayListOf(),
    @SerializedName("available_markets" ) var availableMarkets : ArrayList<String>  = arrayListOf(),
    @SerializedName("disc_number"       ) var discNumber       : Int?               = null,
    @SerializedName("duration_ms"       ) var durationMs       : Int?               = null,
    @SerializedName("explicit"          ) var explicit         : Boolean?           = null,
    @SerializedName("external_urls"     ) var externalUrls     : ExternalUrls?      = ExternalUrls(),
    @SerializedName("href"              ) var href             : String?            = null,
    @SerializedName("id"                ) var id               : String?            = null,
    @SerializedName("is_playable"       ) var isPlayable       : Boolean?           = null,
    @SerializedName("linked_from"       ) var linkedFrom       : LinkedFrom?        = LinkedFrom(),
    @SerializedName("restrictions"      ) var restrictions     : Restrictions?      = Restrictions(),
    @SerializedName("name"              ) var name             : String?            = null,
    @SerializedName("preview_url"       ) var previewUrl       : String?            = null,
    @SerializedName("track_number"      ) var trackNumber      : Int?               = null,
    @SerializedName("type"              ) var type             : String?            = null,
    @SerializedName("uri"               ) var uri              : String?            = null,
    @SerializedName("is_local"          ) var isLocal          : Boolean?           = null

)
