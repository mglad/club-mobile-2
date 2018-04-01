package io.mglad.clubmobile.ui.registration

import android.app.Activity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import io.mglad.clubmobile.R
import io.mglad.clubmobile.base.toolbar.BaseToolbarActivity
import io.mglad.clubmobile.databinding.ActivityRegistrationBinding
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : BaseToolbarActivity<RegistrationPresenter, ActivityRegistrationBinding>(), RegistrationView, View.OnClickListener, TextView.OnEditorActionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registrationButton.setOnClickListener(this)
        password.setOnEditorActionListener(this)
        presenter.onViewCreated()
    }

    override fun title(): String = "Register"

    override fun layoutId(): Int =  R.layout.activity_registration

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        binding.progressVisibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressVisibility = View.INVISIBLE
    }
    override fun instantiatePresenter(): RegistrationPresenter {
        return RegistrationPresenter(this)
    }

    override fun onClick(v: View?) {
        presenter.register(username.text.toString(), password.text.toString(), firstName.text.toString(), lastName.text.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    override fun showUsernameError(error: String) {
        username.error = error
    }

    override fun showPasswordError(error: String) {
        password.error = error
    }

    override fun showFirstNameError(error: String) {
        firstName.error = error
    }

    override fun showLastNameError(error: String) {
        lastName.error = error
    }

    override fun onEditorAction(p0: TextView?, actionId: Int, p2: KeyEvent?): Boolean {
        presenter.register(username.text.toString(), password.text.toString(), firstName.text.toString(), lastName.text.toString())
        return true
    }

    override fun stop() {
        setResult(Activity.RESULT_OK)
        finish()
    }
}
