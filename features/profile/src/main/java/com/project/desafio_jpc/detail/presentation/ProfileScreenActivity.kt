package com.project.desafio_jpc.detail.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project.desafio_jpc.designsystem.theme.AppTheme
import com.project.desafio_jpc.detail.presentation.presentation.route.ProfileRoute

const val PROFILE_ROUTE = "PROFILE_ROUTE"

class ProfileScreenActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            AppTheme {
                NavHost(
                    navController = navController,
                    startDestination = PROFILE_ROUTE
                ) {
                    composable(PROFILE_ROUTE) {
                        ProfileRoute()
                    }

                }
            }

        }
    }
}