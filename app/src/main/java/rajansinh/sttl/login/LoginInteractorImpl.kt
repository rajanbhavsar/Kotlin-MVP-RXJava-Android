package rajansinh.sttl.login

import android.os.Handler
import android.text.TextUtils


/**
 * Created by rajan.bhavsar on 4/5/2018.
 */
public class LoginInteractorImpl : LoginInteractor {


    override fun login(email: String, password: String, listner: LoginInteractor.LoadingFinishListner) {

        val handler: Handler = Handler()
        handler.postDelayed({
            if (TextUtils.isEmpty(email)) {
                listner.onUsernameError()
            }
            if (TextUtils.isEmpty(password)) {
                listner.onPasswordError()
            }
            listner.onSuccess()
        }, 3000)
    }


}