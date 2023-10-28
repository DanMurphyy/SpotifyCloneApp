package com.hfad.spotifycloneapp.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.hfad.spotifycloneapp.data.entities.Song
import com.hfad.spotifycloneapp.other.Constants
import kotlinx.coroutines.tasks.await

class MusicDatabase {
    private val firestore = FirebaseFirestore.getInstance()
    private val songCollection = firestore.collection(Constants.SONG_COLLECTION)

    suspend fun getAllSongs(): List<Song> {
        return try {
            songCollection.get().await().toObjects(Song::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }
}