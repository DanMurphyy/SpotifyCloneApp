package com.hfad.spotifycloneapp.adapters

import androidx.recyclerview.widget.AsyncListDiffer
import com.bumptech.glide.RequestManager
import com.hfad.spotifycloneapp.databinding.ListItemBinding
import com.plcoding.spotifycloneyt.adapters.BaseSongAdapter
import javax.inject.Inject

class SongAdapter @Inject constructor(
    private val glide: RequestManager
) : BaseSongAdapter<ListItemBinding>({ layoutInflater, parent, attachToParent ->
    ListItemBinding.inflate(layoutInflater, parent, attachToParent)
}) {

    override val differ = AsyncListDiffer(this, diffCallback)

    override fun onBindViewHolder(holder: SongViewHolder<ListItemBinding>, position: Int) {
        val song = songs[position]
        val binding = holder.binding
        holder.itemView.apply {

            binding.tvPrimary.text = song.title
            binding.tvSecondary.text = song.subtitle
            glide.load(song.imageUrl).into(binding.ivItemImage)

            setOnClickListener {
                onItemClickListener?.let { click ->
                    click(song)
                }
            }
        }
    }

}