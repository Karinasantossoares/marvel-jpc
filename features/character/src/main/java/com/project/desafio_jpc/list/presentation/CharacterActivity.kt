package com.project.desafio_jpc.list.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.desafio_jpc.designsystem.theme.AppTheme
import com.project.desafio_jpc.list.presentation.detail.CharacterDetailRoute
import com.project.desafio_jpc.list.presentation.list.route.CharacterRoute
import com.project.desafio_jpc.navigation.ProfileNavigation
import org.koin.android.ext.android.inject

private const val CHARACTER_LIST = "CHARACTER_LIST"
private const val PARAM_CHARACTER_ID = "characterId"
private const val CHARACTER_DETAIL = "CHARACTER_LIST?${PARAM_CHARACTER_ID}={${PARAM_CHARACTER_ID}}"

class CharacterActivity : ComponentActivity() {
    private val profileNavigation: ProfileNavigation by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            AppTheme {
                NavHost(
                    navController = navController,
                    startDestination = CHARACTER_LIST
                ) {
                    composable(CHARACTER_LIST) {
                        CharacterRoute(
                            goToDetail = { id ->
                                navController.navigate(
                                    CHARACTER_DETAIL.replace(
                                        "{$PARAM_CHARACTER_ID}",
                                        id
                                    )
                                )
                            },
                            goToProfile = {
                                profileNavigation.goToProfileModule(this@CharacterActivity)
                            }
                        )
                    }

                    composable(
                        route = CHARACTER_DETAIL,
                        arguments = listOf(navArgument(PARAM_CHARACTER_ID) {
                            type = NavType.StringType
                        })
                    ) {
                        CharacterDetailRoute(
                            id = it.arguments?.getString(PARAM_CHARACTER_ID) ?: "",
                            onBack = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}