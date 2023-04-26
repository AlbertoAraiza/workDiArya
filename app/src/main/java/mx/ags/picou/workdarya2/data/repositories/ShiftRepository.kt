package mx.ags.picou.workdarya2.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mx.ags.picou.workdarya2.data.database.ShiftDAO
import mx.ags.picou.workdarya2.models.entitys.ShiftEntity
import javax.inject.Inject

class ShiftRepository @Inject constructor(
    val shiftDAO: ShiftDAO
){
    fun saveShift(shift: ShiftEntity){
        CoroutineScope(Dispatchers.IO).launch{
            shiftDAO.addShift(shift)
        }
    }
    suspend fun readShifts(month :String, year :String): List<ShiftEntity>{
        val comparation = "%-$month-$year"
        Log.d("OBSERVING", comparation)
        return shiftDAO.getShiftsByMonth(comparation)
    }

    fun deleteShift(id :String){
        CoroutineScope(Dispatchers.IO).launch {
            shiftDAO.deleteShifht(id)
        }
    }

}