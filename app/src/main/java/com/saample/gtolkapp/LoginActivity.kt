package com.saample.gtolkapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*


/**
 * Activity to demonstrate anonymous login and account linking (with an email/password account).
 */
class LoginActivity : AppCompatActivity(), View.OnClickListener {

    // [START declare_auth]
    private lateinit var auth: FirebaseAuth
    // [END declare_auth]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // [START initialize_auth]
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        // [END initialize_auth]

        // Click listeners
        loginButton.setOnClickListener(this)
        nologinButton.setOnClickListener(this)
    }
    // [START on_start_check_user]
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }
    // [END on_start_check_user]

    private fun signInAnonymously() {
       // showProgressDialog()
        // [START signin_anonymously]
        auth.signInAnonymously()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInAnonymously:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInAnonymously:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }

                // [START_EXCLUDE]
              //  hideProgressDialog()
                // [END_EXCLUDE]
            }
        // [END signin_anonymously]
    }

    private fun signOut() {
        auth.signOut()
        updateUI(null)
    }
    private fun updateUI(user: FirebaseUser?) {
        //hideProgressDialog()
        val isSignedIn = user != null

        /*Status text
        if (isSignedIn) {
            anonymousStatusId.text = getString(R.string.id_fmt, user!!.uid)
            anonymousStatusEmail.text = getString(R.string.email_fmt, user.email)
        } else {
            anonymousStatusId.setText(R.string.signed_out)
            anonymousStatusEmail.text = null
        }

        */
        // Button visibility
        loginButton.isEnabled = !isSignedIn
        nologinButton.isEnabled = isSignedIn
    }

    override fun onClick(v: View) {
        val i = v.id
        when (i) {
            R.id.loginButton -> signInAnonymously()
            R.id.nologinButton -> signOut()
        }
    }

    companion object {
        private const val TAG = "AnonymousAuth"
    }
}