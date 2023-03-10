package com.gun.hiltexample.module

import android.content.ContentResolver
import android.content.Context
import androidx.room.Room
import com.gun.hiltexample.data.repository.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class UserLocalDataSourceQualifier

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class UserMockDataSourceQualifier

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class UserProviderDataSourceQualifier

    @Binds
    @UserLocalDataSourceQualifier
    abstract fun bindUserLocalDataSource(userLocalDataSource: UserLocalDataSource): UserDataSource

    @Binds
    @UserMockDataSourceQualifier
    abstract fun bindUserMockDataSource(userMockDataSource: UserMockDataSource): UserDataSource

    @Binds
    @UserProviderDataSourceQualifier
    abstract fun bindUserProviderDataSource(userProviderDataSource: UserProviderDataSource): UserDataSource

    companion object {
        @Provides
        @Singleton
        fun provideDB(@ApplicationContext context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java, "User"
            ).build()
        }

        @Provides
        fun provideContentResolver(@ApplicationContext context: Context): ContentResolver {
            return context.contentResolver
        }
    }

}