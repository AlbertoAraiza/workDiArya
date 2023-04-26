package mx.ags.picou.workdarya2.ui.dialogs

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.DatePicker
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import es.dmoral.toasty.Toasty
import mx.ags.picou.workdarya2.core.DateFormatters.Companion.dayFormatter
import mx.ags.picou.workdarya2.core.ShiftKind
import mx.ags.picou.workdarya2.core.ShiftTime
import mx.ags.picou.workdarya2.databinding.DialogNewShiftBinding
import mx.ags.picou.workdarya2.models.entitys.Shift
import java.util.*

class NewShift (private val ctx :Context, private val doOnUpdate :(Shift)->Unit) {
    private var dialogBinding :DialogNewShiftBinding = DialogNewShiftBinding.inflate(LayoutInflater.from(ctx))
    private var dialog : AlertDialog = MaterialAlertDialogBuilder(ctx).setView(dialogBinding.root).create()
    private var  choosenDate = Date()
    init {
        hideCalendar()
        dialogBinding.btnDate.setOnClickListener {
            dialogBinding.dpNewDate.isVisible = !dialogBinding.dpNewDate.isVisible
        }
        dialogBinding.dpNewDate.setOnDateChangedListener { _:DatePicker, y:Int, m:Int, d:Int ->
            Log.d("OBSERVING", "d: $d, m: $m y: $y")
            choosenDate = GregorianCalendar(y, m,d).time
            hideCalendar()
            updateDate()
        }
        val kindsAdapter = ArrayAdapter<String>(ctx, android.R.layout.simple_dropdown_item_1line, ShiftKind.values().map { it.spanishName })
        val timesAdapter = ArrayAdapter<String>(ctx, android.R.layout.simple_dropdown_item_1line, ShiftTime.values().map { shiftTime -> shiftTime.spanishName })
        dialogBinding.btnOk.setOnClickListener {
            val selectedKind = dialogBinding.etShiftKind.editText!!.text.toString()
            val selectedTime = dialogBinding.etShiftTime.editText!!.text.toString()

            val tempShift = Shift().apply {
                dateShift = choosenDate
                kind = ShiftKind.values().first { it.spanishName == selectedKind }
                time = ShiftTime.values().first { it.spanishName == selectedTime }
            }
            doOnUpdate(tempShift)
            dialog.dismiss()
        }

        (dialogBinding.etShiftKind.editText as? AutoCompleteTextView)?.apply {
            setAdapter(kindsAdapter)
        }
        (dialogBinding.etShiftTime.editText as? AutoCompleteTextView)?.apply {
            setAdapter(timesAdapter)
        }
        updateDate()
    }

    private fun updateDate() {
        dialogBinding.btnDate.text = dayFormatter.format(choosenDate)
    }

    private fun hideCalendar(){
        dialogBinding.dpNewDate.isVisible = false
    }

    fun showDialog(){
        dialog.show()
    }
}