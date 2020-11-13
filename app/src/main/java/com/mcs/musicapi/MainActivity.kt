package com.mcs.musicapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_display.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainActivity"
const val KEY_SONG_ITEM: String ="MainActivity_KEY_SONG_ITEM"
class MainActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabs_layout.addOnTabSelectedListener(this)
    }
    private fun getRock(){
        Log.d(TAG, "IN GET ROCK")
        SongsApi.initRetroFit().getRock().enqueue(
            object: Callback<List<SongItem>> {
                override fun onResponse(
                    call: Call<List<SongItem>>,
                    response: Response<List<SongItem>>
                ) {
                    if(response.isSuccessful)
                    {
                        Log.d(TAG, "Success")
                        response.body()?.let {  recycler_music.layoutManager=
                                GridLayoutManager(this@MainActivity,1)
                            recycler_music.adapter = MusicAdapter(it,::openActivityDetails) }

                    }
                }

                override fun onFailure(call: Call<List<SongItem>>, t: Throwable) {
                    Log.d(TAG, "failed")
                    Toast.makeText(this@MainActivity,"Error", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
    private fun getClassics(){
        Log.d(TAG, "IN GET Classic")
        SongsApi.initRetroFit().getClassic().enqueue(
            object: Callback<List<SongItem>> {
                override fun onResponse(
                    call: Call<List<SongItem>>,
                    response: Response<List<SongItem>>
                ) {
                    if(response.isSuccessful)
                    {
                        Log.d(TAG, "Success")
                        response.body()?.let {  }

                    }
                }

                override fun onFailure(call: Call<List<SongItem>>, t: Throwable) {
                    Log.d(TAG, "failed")
                    Toast.makeText(this@MainActivity,"Error", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
    private fun getPop(){
        Log.d(TAG, "IN GET POP")
        SongsApi.initRetroFit().getPop().enqueue(
            object: Callback<List<SongItem>> {
                override fun onResponse(
                    call: Call<List<SongItem>>,
                    response: Response<List<SongItem>>
                ) {
                    if(response.isSuccessful)
                    {
                        Log.d(TAG, "Success")
                        response.body()?.let {  }

                    }
                }

                override fun onFailure(call: Call<List<SongItem>>, t: Throwable) {
                    Log.d(TAG, "failed")
                    Toast.makeText(this@MainActivity,"Error", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
      //  Toast.makeText(this@MainActivity,"TEST: ${tab?.text}", Toast.LENGTH_SHORT).show()
        when(tab?.text)
        {
            "Rock" -> getRock()
            "Classic" ->getClassics()
            "Pop" -> getPop()
        }
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }
    private fun openActivityDetails(songItem: SongItem ){
        val fragment = DetailFragment.newInstance(songItem)
        supportFragmentManager.beginTransaction()
                .replace(R.id.display_fragment_container, fragment).commit()
    }
}



