package com.yuraev.profile.di

import android.content.Context
import com.yuraev.profile.data.LocalResumeRepository
import com.yuraev.profile.data.ResumeRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideResumeRepository(@ApplicationContext context: Context): LocalResumeRepository {
        return LocalResumeRepository(context)
    }
}
