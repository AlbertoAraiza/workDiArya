package mx.ags.picou.workdarya2.data.database

import androidx.room.TypeConverter
import mx.ags.picou.workdarya2.core.ShiftKind
import mx.ags.picou.workdarya2.core.ShiftTime
import java.util.*

class Converters {
//    @TypeConverter
//    fun fromTimestamp(value :Long): Date {
//        return Date(value)
//    }
//    @TypeConverter
//    fun dateToTimestamp(date : Date):Long{
//        return date.time
//    }
    @TypeConverter
    fun shiftKindToText(kind :ShiftKind):String{
        return kind.name
    }
    @TypeConverter
    fun textToShiftKind(kindName :String) :ShiftKind{
        return ShiftKind.values().first { it.name == kindName }
    }
    @TypeConverter
    fun shiftTimeToText(time : ShiftTime):String{
        return time.name
    }
    @TypeConverter
    fun textToShiftTime(timeName :String) :ShiftTime{
        return ShiftTime.values().first { it.name == timeName }
    }
}