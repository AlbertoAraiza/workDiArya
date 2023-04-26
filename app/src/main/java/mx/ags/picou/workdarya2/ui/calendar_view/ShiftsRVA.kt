package mx.ags.picou.workdarya2.ui.calendar_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import mx.ags.picou.workdarya2.R
import mx.ags.picou.workdarya2.core.DateFormatters.Companion.weekDayFormatter
import mx.ags.picou.workdarya2.core.ShiftKind
import mx.ags.picou.workdarya2.databinding.ItemShiftBinding
import mx.ags.picou.workdarya2.models.ShiftDay

class ShiftsRVA : RecyclerView.Adapter<ShiftsRVA.ShiftsVH>() {
    val items = ArrayList<ShiftDay>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShiftsVH {
        val inflater = LayoutInflater.from(parent.context)
        return ShiftsVH(ItemShiftBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ShiftsVH, position: Int) {
        val currentShift = items[position]
        with(holder.itemBinding){
            this.tvDate.text = weekDayFormatter.format(currentShift.date)

            if (currentShift.accumulatedWorking != null){
                llContainer.setBackgroundResource(R.color.cadburyPurple)
                tvEvening.isVisible = false
                tvMorning.isVisible = false
                tvExtra.isVisible = true
            }else{
                llContainer.setBackgroundResource(0)
                tvEvening.isVisible = true
                tvMorning.isVisible = true
                tvExtra.isVisible = false

                if(currentShift.morningShift != null){
                    this.tvMorning.text = currentShift.morningShift!!.spanishName
                    val morningColor = when(currentShift.morningShift){
                        ShiftKind.BaseByBase -> R.color.supremeOrange
                        ShiftKind.Substitution -> R.color.angelBlue
                        else -> R.color.hendersonRed
                    }
                    this.tvMorning.setBackgroundResource(morningColor)
                }else{
                    this.tvMorning.text = null
                }

                if(currentShift.eveningShift != null){
                    this.tvEvening.text = currentShift.eveningShift!!.spanishName
                    val eveningColor = when(currentShift.eveningShift){
                        ShiftKind.BaseByBase -> R.color.primroseYellow
                        ShiftKind.Substitution -> R.color.discordGreen
                        else -> R.color.hendersonRed
                    }
                    this.tvEvening.setBackgroundResource(eveningColor)
                }else{
                    this.tvEvening.text = null
                }
            }
        }
    }

    override fun getItemCount(): Int = items.size

    inner class ShiftsVH(val itemBinding :ItemShiftBinding) :RecyclerView.ViewHolder(itemBinding.root)
}
