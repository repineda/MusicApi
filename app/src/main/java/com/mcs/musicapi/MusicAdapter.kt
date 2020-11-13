package com.mcs.musicapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MusicAdapter(val dataSet: List<SongItem>,
                  val activityCallback:(song: SongItem)->Unit): RecyclerView.Adapter<MusicAdapter.songViewHolder>(){
    class songViewHolder(val songView: View): RecyclerView.ViewHolder(songView)
    {
        private val songImage: ImageView = songView.findViewById(R.id.iv_item_song_art)

        fun onBind(songItem: SongItem, openDetailCallback: (songItem: SongItem)->Unit)
        {
            songView.setOnClickListener{
                openDetailCallback.invoke(songItem)
            }
            songImage.showPoster(songItem.artworkUrl60)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): songViewHolder {
        return songViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.song_item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: MusicAdapter.songViewHolder, position: Int) {
        holder.onBind(dataSet[position], activityCallback)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}