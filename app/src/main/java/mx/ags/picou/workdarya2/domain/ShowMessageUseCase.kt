package mx.ags.picou.workdarya2.domain

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import es.dmoral.toasty.Toasty
import javax.inject.Inject

class ShowMessageUseCase @Inject constructor(
    @ApplicationContext private val ctx :Context
){
    operator fun invoke(msg :String, isError :Boolean = false){
        if (isError) Toasty.error(ctx, msg).show()
        else Toasty.success(ctx, msg).show()
    }
}