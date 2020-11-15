package com.mcs.musicapi

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//https://itunes.apple.com/search?term=rock&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50
interface SongsApi {
    @GET("search?term=rock&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=20")
    fun getRock(): Call<SongResponse>

    @GET("search?term=classick&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=20")
    fun getClassic(): Call<SongResponse>

    @GET("search?term=pop&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=20")
    fun getPop(): Call<SongResponse>

    companion object {
        fun initRetroFit(): SongsApi{
            return Retrofit.Builder().baseUrl("https://itunes.apple.com/")
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(SongsApi::class.java)
        }
    }
}