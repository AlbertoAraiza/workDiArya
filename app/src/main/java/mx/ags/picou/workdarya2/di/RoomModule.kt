package mx.ags.picou.workdarya2.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mx.ags.picou.workdarya2.data.database.AppDataBase
import mx.ags.picou.workdarya2.data.database.ShiftDAO
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext ctx :Context) :AppDataBase{
        return Room.databaseBuilder(ctx, AppDataBase::class.java, "app_db")
            .fallbackToDestructiveMigration().build()
    }
    @Singleton
    @Provides
    fun provideShiftDao(db :AppDataBase) :ShiftDAO{
        return db.getShiftDao()
    }
}