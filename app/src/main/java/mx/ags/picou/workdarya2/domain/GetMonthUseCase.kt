package mx.ags.picou.workdarya2.domain

import mx.ags.picou.workdarya2.core.DateFormatters.Companion.monthFormatter
import java.util.*
import javax.inject.Inject

class GetMonthUseCase @Inject constructor(){
    operator fun invoke(date : Date) :String{
        return monthFormatter.format(date).uppercase()
    }
}