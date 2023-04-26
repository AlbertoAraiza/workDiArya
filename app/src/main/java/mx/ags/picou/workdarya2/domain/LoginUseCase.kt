package mx.ags.picou.workdarya2.domain

import javax.inject.Inject

private const val VALID_EMAIL = "littleangel_love_1@hotmail.com"
private const val VALID_PASSWORD = "5f4dcc3b5aa765d61d8327deb882cf99"
class LoginUseCase @Inject constructor(
    private val encrypt: EncryptUseCase,
    private val showMessage: ShowMessageUseCase
) {
    operator fun invoke(email :String, password :String) :Boolean{
        val encryptedPassword = encrypt(password)
        val result = email.equals(VALID_EMAIL,true) && encryptedPassword == VALID_PASSWORD
        if (result) showMessage("Acceso autorizado")
        else showMessage("Acceso denegado", true)
        return result
    }
}