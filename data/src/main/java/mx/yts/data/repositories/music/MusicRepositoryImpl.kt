package mx.yts.data.repositories.music

import dagger.hilt.android.scopes.ActivityScoped
import mx.yts.data.entities.MusicEntity
import mx.yts.data.providers.MusicProvider
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(var musicProvider: MusicProvider) : MusicRepository {
    override fun getAllMusics(): List<MusicEntity> {
        return musicProvider.getAllMusics()
    }
}