package rajansinh.sttl.login

/**
 * Created by rajan.bhavsar on 4/5/2018.
 */
public class LoginPresentatorImpl : LoginPresentator, LoginInteractor.LoadingFinishListner {

    private var loginView: LoginView? = null
    internal lateinit var loginInteractor: LoginInteractor

    constructor(loginView: LoginView, loginInteractor: LoginInteractor) {
        this.loginView = loginView
        this.loginInteractor = loginInteractor;
    }

    override fun validateCredential(email: String, password: String) {
        if (loginView != null) {
            loginView!!.ShowProgress()
        }

        loginInteractor.login(email, password, this)

    }

    override fun onDestroy() {
        loginView = null
    }


    override fun onUsernameError() {
        if (loginView != null) {
            loginView!!.ShowEmailError()
            loginView!!.HideProgress()
        }
    }

    override fun onPasswordError() {
        if (loginView != null) {
            loginView!!.ShowPasswordError()
            loginView!!.HideProgress()
        }
    }

    override fun onSuccess() {
        if (loginView != null) {
            loginView!!.NavigateToOtherScreen()
            loginView!!.HideProgress()
        }
    }
}