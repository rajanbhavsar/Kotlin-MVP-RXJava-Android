package rajansinh.sttl.login

/**
 * Created by rajan.bhavsar on 4/5/2018.
 */
public interface LoginView {

    fun ShowProgress()

    fun ShowEmailError()

    fun ShowPasswordError()

    fun HideProgress()

    fun NavigateToOtherScreen()
}