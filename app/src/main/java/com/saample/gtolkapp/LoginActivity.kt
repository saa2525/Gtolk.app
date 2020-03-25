package com.saample.gtolkapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var mCreateAccountListener: OnCompleteListener<AuthResult>
    private lateinit var mLoginListener: OnCompleteListener<AuthResult>
    private lateinit var mDataBaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener { v ->
            // FirebaseAuthのオブジェクトを取得
            auth = FirebaseAuth.getInstance()
            Log.d("current", "currentUser = ${auth.currentUser?.uid.toString()}")
            signInAnonymously()
        }
    }

    //匿名認証リスナー
    private fun signInAnonymously() {
        auth.signInAnonymously()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // 成功した場合, update UI with the signed-in user's information
                    Log.d("success", "currentUser = ${auth.currentUser?.uid.toString()}")

                } else {
                    // 失敗した場合, display a message to the user.
                    Log.d("error", task.exception.toString())
                }
            }
    }

}

