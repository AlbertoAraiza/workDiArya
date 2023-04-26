package mx.ags.picou.workdarya2.models.entitys

import mx.ags.picou.workdarya2.core.DateFormatters.Companion.dayFormatter
import mx.ags.picou.workdarya2.core.ShiftKind
import mx.ags.picou.workdarya2.core.ShiftTime
import java.util.*

class Shift {
    fun toEntity(): ShiftEntity {
        return ShiftEntity(
            shiftDate = dayFormatter.format(this.dateShift),
            shiftKind = this.kind,
            shiftTime = this.time
        )
    }

    var dateShift : Date = Date()
    var kind :ShiftKind = ShiftKind.Other
    var time :ShiftTime = ShiftTime.Morning
}