package mx.yts.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import mx.yts.data.providers.MusicProvider
import mx.yts.data.repositories.music.MusicRepository
import mx.yts.data.repositories.music.MusicRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryBindsModule {

    @Binds
    abstract fun provideMusicRepository(musicRepositoryImpl: MusicRepositoryImpl): MusicRepository

}
