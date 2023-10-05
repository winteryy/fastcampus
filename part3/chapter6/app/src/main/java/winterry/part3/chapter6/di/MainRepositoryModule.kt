package winterry.part3.chapter6.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import winterry.part3.chapter6.remote.MainService
import winterry.part3.chapter6.remote.repository.MainRepository
import winterry.part3.chapter6.remote.repository.MainRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
object MainRepositoryModule {

    @ViewModelScoped
    @Provides
    fun providesRepository(
        mainService: MainService
    ): MainRepository = MainRepositoryImpl(mainService)
}