package com.hfad.spotifycloneapp.adapters

import androidx.recyclerview.widget.AsyncListDiffer
import com.hfad.spotifycloneapp.databinding.SwipeItemBinding
import com.plcoding.spotifycloneyt.adapters.BaseSongAdapter

class SwipeSongAdapter :
    BaseSongAdapter<SwipeItemBinding>({ layoutInflater, parent, attachToParent ->
        SwipeItemBinding.inflate(layoutInflater, parent, attachToParent)
    }) {

    override val differ = AsyncListDiffer(this, diffCallback)


    override fun onBindViewHolder(holder: SongViewHolder<SwipeItemBinding>, position: Int) {
        val song = songs[position]
        val binding = holder.binding
        holder.itemView.apply {

            val text = "${song.title} -${song.subtitle}"

            binding.tvPrimary.text = text

            setOnClickListener {
                onItemClickListener?.let { click ->
                    click(song)
                }
            }
        }
    }

}