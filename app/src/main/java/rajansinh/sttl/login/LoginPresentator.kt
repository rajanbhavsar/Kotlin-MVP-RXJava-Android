package rajansinh.sttl.login

/**
 * Created by rajan.bhavsar on 4/5/2018.
 */
public interface LoginPresentator {

    fun validateCredential(email: String, password: String)

    fun onDestroy()
}