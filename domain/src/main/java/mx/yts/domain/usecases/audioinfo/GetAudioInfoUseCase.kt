package mx.yts.domain.usecases.audioinfo

import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.yts.data.repositories.music.MusicRepository
import mx.yts.domain.BaseUseCase
import mx.yts.domain.mapper.MusicEntityMapper
import mx.yts.domain.model.MusicModel
import javax.inject.Inject

class GetAudioInfoUseCase @AssistedInject constructor(
    private var musicRepository: MusicRepository,
    private var musicEntityMapper: MusicEntityMapper,
    @Assisted var audioId: String,
) : BaseUseCase<MusicModel> {
    override suspend fun invoke(): MusicModel = withContext(Dispatchers.Default) {
        musicEntityMapper.mapTo(musicRepository.getMusic(audioId))
    }

}