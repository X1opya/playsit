package dev.playsit.auth

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import dev.playsit.R

class GoogleAuth(private val activity: AppCompatActivity) {
    fun auth() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(activity, gso).signInIntent
        activity.startActivityForResult(googleSignInClient, 10)
    }

    fun onResult(requestCode: Int, data: Intent?) {
        if (requestCode == 10) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d("TEST_AUTH", "id:" + account.id)
                Log.d("TEST_AUTH", "email:" + account.email)
                Log.d("TEST_AUTH", "photoUrl:" + account.photoUrl)
                Log.d("TEST_AUTH", "displayName:" + account.displayName)
                Log.d("TEST_AUTH", "familyName:" + account.familyName)
                Log.d("TEST_AUTH", "givenName:" + account.givenName)
            } catch (e: ApiException) {
                Log.w("TEST_AUTH", "Google sign in failed", e)
            }
        }
    }

    companion object
}
