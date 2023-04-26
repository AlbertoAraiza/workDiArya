package mx.ags.picou.workdarya2.ui.splash_view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import mx.ags.picou.workdarya2.ui.login_view.LoginActivity

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val ctx : Context by lazy { this }
    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()
        super.onCreate(savedInstanceState)
        screenSplash.setKeepOnScreenCondition{ true }
        val intent = Intent(ctx, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}