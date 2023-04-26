package mx.ags.picou.workdarya2.domain

import mx.ags.picou.workdarya2.data.repositories.ShiftRepository
import mx.ags.picou.workdarya2.models.entitys.Shift
import javax.inject.Inject

class AddNewShiftUseCase @Inject constructor(
    private val shiftRepository: ShiftRepository
) {
    operator fun invoke(shift : Shift){
        shiftRepository.saveShift(shift.toEntity())
    }
}