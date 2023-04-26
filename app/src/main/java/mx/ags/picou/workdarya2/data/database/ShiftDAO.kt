package mx.ags.picou.workdarya2.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import mx.ags.picou.workdarya2.models.entitys.ShiftEntity

@Dao
interface ShiftDAO {
    @Query("SELECT * FROM shift WHERE shift_date LIKE :comparation")
    suspend fun getShiftsByMonth(comparation :String) :List<ShiftEntity>

    @Insert
    suspend fun addShift(shift :ShiftEntity)

    @Query("DELETE FROM shift WHERE id = :id")
    suspend fun deleteShifht(id :String)
}