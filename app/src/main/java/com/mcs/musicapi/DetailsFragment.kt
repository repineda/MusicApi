package com.mcs.musicapi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso

class DetailFragment: Fragment(){

    interface RefreshListener{
        fun refreshCalled()
    }

    var  myListener : RefreshListener? = null

    companion object{
        fun newInstance(songItem: SongItem): DetailFragment{
            return DetailFragment().also{
                it.arguments = Bundle().also {
                    it.putParcelable(KEY_SONG_ITEM,
                            songItem)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(
                R.layout.fragment_display_detail,
                container,
                false
        )
        arguments?.getParcelable<SongItem>(KEY_SONG_ITEM)?.let{
            view.findViewById<TextView>(R.id.tv_artist_name).text = it.artistName
            view.findViewById<TextView>(R.id.tv_collection_name).text = it.collectionName
            view.findViewById<TextView>(R.id.tv_price).text = it.trackPrice.toString()
            view.findViewById<ImageView>(R.id.iv_song_art).showPoster(it.artworkUrl60)
        }
        return view
    }
}

fun ImageView.showPoster(url: String){
    Picasso.get().load(url).into(this)
}