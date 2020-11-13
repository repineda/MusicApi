package com.mcs.musicapi

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SongResponse(
        val results: SongItem

):Parcelable

data class SongItem(
    val artistName: String,
    val collectionName: String,
    val artworkUrl60: String,
    val trackPrice: Float,
    val previewUrl: String
):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString() ?: "N/A",
            parcel.readString() ?: "N/A",
            parcel.readString() ?: "N/A",
            parcel.readFloat(),
            parcel.readString() ?: "N/A"){
    }
    override fun describeContents(): Int {
        return Parcelable.CONTENTS_FILE_DESCRIPTOR
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.let {    parcel->
            parcel.writeString(artistName)
            parcel.writeString(collectionName)
            parcel.writeString(artworkUrl60)
            parcel.writeFloat(trackPrice)
            parcel.writeString(previewUrl)
            }
    }
    companion object CREATOR : Parcelable.Creator<SongItem> {
        override fun createFromParcel(parcel: Parcel): SongItem {
            return SongItem(parcel)
        }

        override fun newArray(size: Int): Array<SongItem?> {
            return arrayOfNulls(size)
        }
    }
}