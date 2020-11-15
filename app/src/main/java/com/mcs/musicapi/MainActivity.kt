package com.mcs.musicapi

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainActivity"
const val KEY_SONG_ITEM: String ="MainActivity_KEY_SONG_ITEM"

private lateinit var api: () -> Call<SongResponse>
class MainActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener, DetailFragment.RefreshListener{

    private lateinit var rock: CharSequence
    private lateinit var classic: CharSequence
    private lateinit var pop: CharSequence

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabs_layout.addOnTabSelectedListener(this)

        swipe_refresh.setOnRefreshListener {
            viewSongs(api)
        }



        rock = "Rock"
        classic = "Classic"
        pop = "Pop"

        api = SongsApi.initRetroFit()::getRock
        viewSongs(api)
    }
    private fun viewSongs(api: ()-> Call<SongResponse>){
        swipe_refresh.isRefreshing = false
        api.invoke().enqueue(
            object: Callback<SongResponse> {
                override fun onResponse(
                    call: Call<SongResponse>,
                    response: Response<SongResponse>
                ) {
                    if(response.isSuccessful)
                    {
                        Log.d(TAG, "Success")
                        response.body()?.let {  recycler_music.layoutManager=
                                GridLayoutManager(this@MainActivity,1)
                            recycler_music.adapter = MusicAdapter(it,::playMusic) }

                    }
                }

                override fun onFailure(call: Call<SongResponse>, t: Throwable) {
                    Log.d(TAG, "failed")
                    Toast.makeText(this@MainActivity,"Error", Toast.LENGTH_SHORT).show()
                }
            }
        )
        swipe_refresh.isRefreshing = false
    }



    override fun onTabSelected(tab: TabLayout.Tab?) {
        when(tab?.text)
        {
            rock -> api = SongsApi.initRetroFit()::getRock
            classic -> api = SongsApi.initRetroFit()::getClassic
            pop -> api = SongsApi.initRetroFit()::getPop
        }
        viewSongs(api)
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        //Do Nothing
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        //Do Nothing
    }
    private fun playMusic(songItem: SongItem ){
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.setDataAndType(Uri.parse(songItem.previewUrl), if (false) "video/mp4" else "audio/mp3")
        startActivity(intent)
    }

    override fun refreshCalled() {
        swipe_refresh.isRefreshing = false
        val currTab = tabs_layout.getTabAt(tabs_layout.selectedTabPosition)
        when(currTab?.text)
        {
            rock -> api = SongsApi.initRetroFit()::getRock
            classic -> api = SongsApi.initRetroFit()::getClassic
            pop -> api = SongsApi.initRetroFit()::getPop
        }
        viewSongs(api)
    }

}



