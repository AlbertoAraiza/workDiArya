package mx.ags.picou.workdarya2.models

import mx.ags.picou.workdarya2.core.ShiftKind
import java.util.*

class ShiftDay {
    var date : Date = Date()
    var morningShift :ShiftKind? = null
    var eveningShift :ShiftKind? = null
    var accumulatedWorking :ShiftKind? = null
}