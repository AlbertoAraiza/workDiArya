package mx.ags.picou.workdarya2.ui.login_view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import dagger.hilt.android.AndroidEntryPoint
import mx.ags.picou.workdarya2.databinding.ActivityLoginBinding
import mx.ags.picou.workdarya2.ui.calendar_view.CalendarActivity

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private val vm :LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm.emailError.observe(this){
            Log.d("OBSERVING", "observando email error")
            if (it.isNullOrEmpty()) binding.etEmail.error = null
            else binding.etEmail.error = it
        }
        vm.passwordError.observe(this){
            Log.d("OBSERVING", "observando password error")
            if (it.isNullOrEmpty()) binding.etPassword.error = null
            else binding.etPassword.error = it
        }
        vm.enableLogin.observe(this){
            Log.d("OBSERVING", "observando enable login")
            binding.btnLogin.isEnabled = it
        }
        vm.passwordClear.observe(this){
            Log.d("OBSERVING", "observando password")
            if(it) binding.etPassword.editText!!.text.clear()
        }

        with(binding.etEmail){
            editText!!.doAfterTextChanged {
                Log.d("OBSERVING", "email changing")
                vm.onEmailChange(it.toString())
            }
        }
//
        with(binding.etPassword){
            editText!!.doAfterTextChanged {
                Log.d("OBSERVING", "password changing")
                vm.onPasswordChange(it.toString())
            }
        }
    }

    fun onButtonPressed(view: View) {
        when(view.id){
            binding.btnLogin.id-> if(vm.onLoginPressed()) navToCalendar()
        }
    }

    private fun navToCalendar() {
        val intent = Intent(this, CalendarActivity::class.java)
        startActivity(intent)
        finish()
    }
}