package mx.ags.picou.workdarya2.domain

import mx.ags.picou.workdarya2.core.DateFormatters.Companion.dayFormatter
import mx.ags.picou.workdarya2.core.ShiftTime
import mx.ags.picou.workdarya2.data.repositories.ShiftRepository
import mx.ags.picou.workdarya2.models.ShiftDay
import mx.ags.picou.workdarya2.models.entitys.ShiftEntity
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class GetShiftsDayForMonth @Inject constructor(
    private val shiftRepository: ShiftRepository
){

    suspend operator fun invoke(date : GregorianCalendar) :List<ShiftDay>{
        val month = date.get(GregorianCalendar.MONTH)
        val year = date.get(GregorianCalendar.YEAR)
        val fMonth = String.format("%02d",month+1)
        val fYear = year.toString()
        val shifts = shiftRepository.readShifts(fMonth, fYear)
        return shifts.toDays()
    }
}

private fun List<ShiftEntity>.toDays(): List<ShiftDay> {
    val days = ArrayList<ShiftDay>()
    for (shift in this){
        var flagAdded = false
        for(day in days){
            if (day.date == dayFormatter.parse(shift.shiftDate)){
                when(shift.shiftTime){
                    ShiftTime.Morning-> day.morningShift = shift.shiftKind
                    ShiftTime.Evening -> day.eveningShift = shift.shiftKind
                    ShiftTime.AccumulatedWorkingDay -> day.accumulatedWorking = shift.shiftKind
                }
                flagAdded = true
            }
        }
        if (!flagAdded){
            val day = ShiftDay().apply {
                this.date = dayFormatter.parse(shift.shiftDate)
            }
            when(shift.shiftTime){
                ShiftTime.Morning-> day.morningShift = shift.shiftKind
                ShiftTime.Evening -> day.eveningShift = shift.shiftKind
                ShiftTime.AccumulatedWorkingDay -> day.accumulatedWorking = shift.shiftKind
            }
            days.add(day)
        }
    }
    return days
}
