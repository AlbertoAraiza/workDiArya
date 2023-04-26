package mx.ags.picou.workdarya2.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.security.MessageDigest
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EncryptModule {
    @Singleton
    @Provides
    fun provideMd5() :MessageDigest{
        return MessageDigest.getInstance("md5")
    }
}