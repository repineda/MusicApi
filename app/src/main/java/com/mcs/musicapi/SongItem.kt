package com.mcs.musicapi

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class SongResponse(val results: List<SongItem>){

}
@Parcelize
data class SongItem(
    val artistName: String,
    val collectionName: String,
    val artworkUrl60: String,
    val trackPrice: Float,
    val previewUrl: String
):Parcelable