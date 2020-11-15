package com.mcs.musicapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MusicAdapter(val dataSet: SongResponse,
                  val activityCallback:(song: SongItem)->Unit):
        RecyclerView.Adapter<MusicAdapter.SongViewHolder>(){

    class SongViewHolder(val songView: View): RecyclerView.ViewHolder(songView)
    {
        private val songImage: ImageView = songView.findViewById(R.id.iv_song_art)
        private val artistName: TextView = songView.findViewById(R.id.tv_artist_name)
        private val collectionName: TextView = songView.findViewById(R.id.tv_collection_name)
        private val price: TextView = songView.findViewById(R.id.tv_price)

        fun onBind(songItem: SongItem, openDetailCallback: (songItem: SongItem)->Unit)
        {
            songView.setOnClickListener{
                openDetailCallback.invoke(songItem)
            }
            artistName.text = songItem.artistName
            collectionName.text = songItem.collectionName
            price.text = songItem.trackPrice.toString()
            songImage.showPoster(songItem.artworkUrl60)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder(LayoutInflater
                .from(parent.context)
                .inflate(R.layout.fragment_display_detail, parent, false))
    }

    override fun onBindViewHolder(holder: MusicAdapter.SongViewHolder, position: Int) {
        holder.onBind(dataSet.results[position], activityCallback)
    }

    override fun getItemCount(): Int {
        return dataSet.results.size
    }
}