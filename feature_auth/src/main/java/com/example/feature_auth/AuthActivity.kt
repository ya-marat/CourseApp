package com.example.feature_auth

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

       // val deps = (application as AuthDependenciesProvider).provideAuthDependencies()

            // val authComponent  = DaggerAuthComponent.factory().create(deps)
        setContentView(R.layout.activity_auth)
    }
}