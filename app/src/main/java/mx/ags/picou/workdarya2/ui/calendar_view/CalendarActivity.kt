package mx.ags.picou.workdarya2.ui.calendar_view

import android.content.Context
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import mx.ags.picou.workdarya2.R
import mx.ags.picou.workdarya2.databinding.ActivityCalendarBinding
import mx.ags.picou.workdarya2.databinding.DialogNewShiftBinding
import mx.ags.picou.workdarya2.models.entitys.Shift
import mx.ags.picou.workdarya2.ui.dialogs.NewShift
import mx.ags.picou.workdarya2.ui.login_view.LoginViewModel

@AndroidEntryPoint
class CalendarActivity : AppCompatActivity() {
    private lateinit var binding :ActivityCalendarBinding
    private val vm : CalendarViewModel by viewModels()
    private val newShiftDialog :NewShift by lazy {
        NewShift(this){newShit : Shift ->
            vm.addShift(newShit)
        } }
    private val ctx : Context by lazy { this }

    override fun onCreate(savedInstanceState: Bundle?) {
        print("Esto es un error")
        print("Esto es un error")
        print("Esto es un error")
        print("Esto es un error")
        print("Esto es un error")
        print("Esto es un error")
        print("Esto es un error")
        print("Esto es un error")
        print("Esto es un error")
        print("Esto es un error")
        print("Esto es un error")
        print("Esto es un error")
        print("Esto es un error")
        print("Esto es un error")
        print("Esto es un error")
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm.monthTitle.observe(this){
            binding.tvMonthTitle.text = it
        }
        binding.rvDates.apply {
            adapter = vm.shiftsAdapter
            layoutManager = LinearLayoutManager(ctx)
        }
    }

    fun onButtonPressed(view: View) {
        when(view){
            binding.ivPrevious -> vm.onPrevPressed()
            binding.ivNext -> vm.onNextPressed()
            binding.fabAddDate -> onAddDatePressed()
        }
    }

    private fun onAddDatePressed() {
        newShiftDialog.showDialog()
    }
}