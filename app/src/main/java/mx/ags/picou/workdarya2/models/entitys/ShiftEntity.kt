package mx.ags.picou.workdarya2.models.entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import mx.ags.picou.workdarya2.core.ShiftKind
import mx.ags.picou.workdarya2.core.ShiftTime
import java.util.*

@Entity("shift")
data class ShiftEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo("id") var id :Int = 0,
    @ColumnInfo("shift_date") var shiftDate : String,
    @ColumnInfo("shift_kind") var shiftKind : ShiftKind,
    @ColumnInfo("shift_time") var shiftTime: ShiftTime
)
