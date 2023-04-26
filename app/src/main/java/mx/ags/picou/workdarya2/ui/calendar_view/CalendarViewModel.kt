package mx.ags.picou.workdarya2.ui.calendar_view

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mx.ags.picou.workdarya2.domain.AddNewShiftUseCase
import mx.ags.picou.workdarya2.domain.GetMonthUseCase
import mx.ags.picou.workdarya2.domain.GetShiftsDayForMonth
import mx.ags.picou.workdarya2.domain.ShowMessageUseCase
import mx.ags.picou.workdarya2.models.entitys.Shift
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val getMonth: GetMonthUseCase,
    private val showMessage: ShowMessageUseCase,
    private val getShiftsDayForMonth: GetShiftsDayForMonth,
    private val addNewShift: AddNewShiftUseCase
) :ViewModel() {
    //private state
    private val _monthTitle = MutableLiveData<String>("")
    //public state
    val monthTitle :LiveData<String> get() = _monthTitle
    //Atributes
    private val currentDate = GregorianCalendar()
    val shiftsAdapter = ShiftsRVA()
    //Methods
    init{
        updateMonth()
    }
    fun onPrevPressed(){
        var currentMonth = currentDate.get(GregorianCalendar.MONTH)
        var currentYear = currentDate.get(GregorianCalendar.YEAR)

        Log.d("OBSERVING", "Current month: $currentMonth")
        Log.d("OBSERVING", "Current year: $currentYear")

        if(currentMonth == 0) {
            currentMonth = 12
            currentYear--
        }
        currentDate.set(GregorianCalendar.MONTH, --currentMonth)
        currentDate.set(GregorianCalendar.YEAR, currentYear)
        updateMonth()
    }

    fun onNextPressed(){
        var currentMonth = currentDate.get(GregorianCalendar.MONTH)
        var currentYear = currentDate.get(GregorianCalendar.YEAR)

        Log.d("OBSERVING", "Current month: $currentMonth")
        Log.d("OBSERVING", "Current year: $currentYear")

        if(currentMonth == 11) {
            currentMonth = -1
            currentYear++
        }
        currentDate.set(GregorianCalendar.MONTH, ++currentMonth)
        currentDate.set(GregorianCalendar.YEAR, currentYear)
        updateMonth()
    }

    private fun updateMonth(){
        _monthTitle.postValue(getMonth(currentDate.time))
        CoroutineScope(Dispatchers.IO).launch {

            shiftsAdapter.items.clear()
            shiftsAdapter.items.addAll(getShiftsDayForMonth(currentDate))
            withContext(Dispatchers.Main) {
                shiftsAdapter.notifyDataSetChanged()
            }
        }
    }

    fun addShift(newShift: Shift) {
        addNewShift(newShift)
        CoroutineScope(Dispatchers.IO).launch {
            shiftsAdapter.items.clear()
            shiftsAdapter.items.addAll(getShiftsDayForMonth(currentDate))
            withContext(Dispatchers.Main) {
                shiftsAdapter.notifyDataSetChanged()
            }
        }
        showMessage(newShift.dateShift.toString())

    }
}