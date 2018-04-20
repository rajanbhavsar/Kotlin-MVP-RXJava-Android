package rajansinh.sttl.login

/**
 * Created by rajan.bhavsar on 4/5/2018.
 */
public interface LoginInteractor {

    interface LoadingFinishListner {
        fun onUsernameError()

        fun onPasswordError()

        fun onSuccess()
    }

    fun login(email: String, password: String,listner:LoadingFinishListner)
}