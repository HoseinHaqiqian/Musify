package mx.yts.domain.usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.yts.data.repositories.music.MusicRepository
import mx.yts.domain.BaseUseCase
import mx.yts.domain.mapper.MusicEntityMapper
import mx.yts.domain.model.MusicModel
import javax.inject.Inject

class GetMusicListUseCase @Inject constructor(
    private var musicRepository: MusicRepository,
    private var musicEntityMapper: MusicEntityMapper
) : BaseUseCase<List<MusicModel>> {

    override suspend fun invoke(): List<MusicModel> = withContext(Dispatchers.IO) {
        musicRepository.getAllMusics().map { musicEntityMapper.mapTo(it) }
    }
}