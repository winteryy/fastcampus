package winterry.part3.chapter6.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import winterry.part3.chapter6.remote.MainService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainServiceModule {

    @Provides
    @Singleton
    fun providesMainService(retrofit: Retrofit): MainService = retrofit.create(MainService::class.java)
}