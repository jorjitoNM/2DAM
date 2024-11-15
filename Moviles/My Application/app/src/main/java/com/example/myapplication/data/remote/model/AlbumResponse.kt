package com.example.myapplication.data.remote.model

import com.google.gson.annotations.SerializedName

data class Album (

  @SerializedName("album_type"             ) var albumType            : String?               = null,
  @SerializedName("total_tracks"           ) var totalTracks          : Int?                  = null,
  @SerializedName("available_markets"      ) var availableMarkets     : ArrayList<String>     = arrayListOf(),
  @SerializedName("external_urls"          ) var externalUrls         : ExternalUrls?         = ExternalUrls(),
  @SerializedName("href"                   ) var href                 : String?               = null,
  @SerializedName("id"                     ) var id                   : String?               = null,
  @SerializedName("images"                 ) var images               : ArrayList<Images>     = arrayListOf(),
  @SerializedName("name"                   ) var name                 : String                ,
  @SerializedName("release_date"           ) var releaseDate          : String?               = null,
  @SerializedName("release_date_precision" ) var releaseDatePrecision : String?               = null,
  @SerializedName("restrictions"           ) var restrictions         : Restrictions?         = Restrictions(),
  @SerializedName("type"                   ) var type                 : String?               = null,
  @SerializedName("uri"                    ) var uri                  : String?               = null,
  @SerializedName("artists"                ) var artists              : ArrayList<Artists>    = arrayListOf(),
  @SerializedName("tracks"                 ) var tracks               : Tracks               = Tracks(),
  @SerializedName("copyrights"             ) var copyrights           : ArrayList<Copyrights> = arrayListOf(),
  @SerializedName("external_ids"           ) var externalIds          : ExternalIds?          = ExternalIds(),
  @SerializedName("genres"                 ) var genres               : ArrayList<String>     = arrayListOf(),
  @SerializedName("label"                  ) var label                : String?               = null,
  @SerializedName("popularity"             ) var popularity           : Int?                  = null
)
fun Album.getSongsList() {
  val track : Tracks = this.tracks
  val songs : ArrayList<SongRemote> = ArrayList()
  track.items.forEach { item -> songs.add(SongRemote(
    item.name,
    item.artists,
    item.durationMs,
    item.explicit,
    this.images[images.lastIndex].url)) }
}
