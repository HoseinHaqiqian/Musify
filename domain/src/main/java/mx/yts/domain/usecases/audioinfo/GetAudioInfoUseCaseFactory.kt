package mx.yts.domain.usecases.audioinfo

import dagger.assisted.AssistedFactory

@AssistedFactory
interface GetAudioInfoUseCaseFactory  {

    fun create(audioId :String) : GetAudioInfoUseCase

}