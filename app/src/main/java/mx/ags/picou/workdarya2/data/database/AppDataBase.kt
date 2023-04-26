package mx.ags.picou.workdarya2.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import mx.ags.picou.workdarya2.models.entitys.ShiftEntity

@Database(entities = [ShiftEntity::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDataBase :RoomDatabase(){
    abstract fun getShiftDao() :ShiftDAO
}