package mx.ags.picou.workdarya2.ui.login_view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import mx.ags.picou.workdarya2.domain.LoginUseCase
import mx.ags.picou.workdarya2.domain.ShowMessageUseCase
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val login: LoginUseCase,
    private val showMessage: ShowMessageUseCase
) :ViewModel() {
    //State private
    private val _emailError = MutableLiveData<String?>(null)
    private val _passwordError = MutableLiveData<String?>(null)
    private val _passwordClear = MutableLiveData<Boolean>(false)
    private val _enableLogin = MutableLiveData<Boolean>(false)

    //State public
    val emailError :LiveData<String?> get() = _emailError
    val passwordError :LiveData<String?> get() = _passwordError
    val passwordClear :LiveData<Boolean> get() = _passwordClear
    val enableLogin :LiveData<Boolean> get() = _enableLogin

    //Atributes
    private var email = ""
    private var password = ""

    fun onEmailChange(newEmail: String) {
        this.email = newEmail
        _emailError.postValue(null)
        checkLoginAvailability()
    }

    fun onPasswordChange(newPassword: String) {
        this.password = newPassword
        _passwordError.postValue(null)
        _passwordClear.value = false
        checkLoginAvailability()
    }

    fun onLoginPressed() :Boolean{
        _passwordClear.postValue(true)
        return login(this.email, this.password)
    }
    private fun checkLoginAvailability(){
        _enableLogin.postValue(this.password.isNotEmpty() && this.email.isNotEmpty())
    }
}