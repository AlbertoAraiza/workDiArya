package mx.ags.picou.workdarya2.domain

import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject

class EncryptUseCase @Inject constructor(
    private val md5 :MessageDigest
){

    operator fun invoke(msg :String) :String{
        return BigInteger(1,md5.digest(msg.toByteArray())).toString(16).padStart(32,'0')
    }
}