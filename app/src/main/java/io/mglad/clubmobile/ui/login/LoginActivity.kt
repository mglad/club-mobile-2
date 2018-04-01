package io.mglad.clubmobile.ui.login

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import io.mglad.clubmobile.R
import io.mglad.clubmobile.base.BaseActivity
import io.mglad.clubmobile.databinding.ActivityLoginBinding
import io.mglad.clubmobile.ui.dashboard.DashboardActivity
import io.mglad.clubmobile.ui.registration.RegistrationActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<LoginPresenter, ActivityLoginBinding>(), LoginView, View.OnClickListener, TextView.OnEditorActionListener {
    companion object {
        val REGISTRATION_REQUST = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginButton.setOnClickListener(this)
        registrationButton.setOnClickListener(this)

        passwordEditText.setOnEditorActionListener(this)
        presenter.onViewCreated()
    }

    override fun layoutId(): Int = R.layout.activity_login

    override fun navigateToDashboard() {
        startActivity(Intent(this, DashboardActivity::class.java))
        finish()
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        binding.signInText = getString(R.string.signing_in)
        binding.progressVisibility = View.VISIBLE
    }
    override fun hideLoading() {
        binding.signInText = getString(R.string.sign_in)
        binding.progressVisibility = View.INVISIBLE
    }

    override fun instantiatePresenter(): LoginPresenter {
        return LoginPresenter(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.loginButton -> presenter.login(usernameEditText.text.toString(), passwordEditText.text.toString())
            R.id.registrationButton -> presenter.register()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    override fun showUsernameError(error: String) {
        usernameEditText.error = error
    }

    override fun showPasswordError(error: String) {
        passwordEditText.error = error
    }

    override fun onEditorAction(p0: TextView?, actionId: Int, p2: KeyEvent?): Boolean {
        presenter.login(usernameEditText.text.toString(), passwordEditText.text.toString())
        return true
    }

    override fun goToRegistration() {
        startActivityForResult(Intent(this, RegistrationActivity::class.java), REGISTRATION_REQUST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REGISTRATION_REQUST) {
            if(resultCode == Activity.RESULT_OK) {
                presenter.registrationSuccess()
            }
        }
    }

    override fun showRegistrationSuccess() {
        Snackbar.make(loginView, "Registration successful", Snackbar.LENGTH_LONG).show()
    }
}
