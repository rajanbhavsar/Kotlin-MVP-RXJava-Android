package rajansinh.sttl.login

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.screen_login.*
import rajansinh.sttl.com.R
import rajansinh.sttl.com.databinding.ScreenLoginBinding
import rajansinh.sttl.home_screen.MainScreen

/**
 * Created by rajan.bhavsar on 4/5/2018.
 */
public class LoginScreen : AppCompatActivity(), LoginView {

    lateinit var mainBinding: ScreenLoginBinding
    lateinit var login_presenter: LoginPresentator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.screen_login)
        var loginInteractor: LoginInteractorImpl = LoginInteractorImpl()
        login_presenter = LoginPresentatorImpl(this, loginInteractor)
        submit.setOnClickListener(View.OnClickListener {
            login_presenter.validateCredential(email.getText().toString(), password.getText().toString())
        })
    }


    override fun ShowProgress() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun ShowEmailError() {
        email.setError("Please Enter Valid Email Address")
    }

    override fun ShowPasswordError() {
        password.setError("Please Enter Password")
    }

    override fun HideProgress() {
        progress_bar.visibility = View.GONE
    }

    override fun NavigateToOtherScreen() {

        var intentfire: Intent = Intent(applicationContext, MainScreen::class.java)
        startActivity(intentfire)
        finish()
    }
}