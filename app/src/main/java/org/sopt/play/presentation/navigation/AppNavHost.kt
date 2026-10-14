package org.sopt.play.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.sopt.play.data.model.UserModel
import org.sopt.play.presentation.login.ui.LoginRoute
import org.sopt.play.presentation.register.ui.RegisterRoute

@Composable
fun AppNavHost(
    onRegister:(UserModel) -> Unit,
    onLogin:(UserModel) -> Boolean,
    ) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = LoginRoute,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None },
    ) {
        composable<LoginRoute> {
            LoginRoute(
                onLoginClick = { user ->
                    if(onLogin(user)) {
                        navController.navigate(MainRoute) {
                            popUpTo<LoginRoute> {
                                inclusive = true
                            }

                            launchSingleTop = true
                        }
                    }
                },
                onRegisterClick = {
                    navController.navigate(RegisterRoute) {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<RegisterRoute> {
            RegisterRoute(
                onRegisterClick = { user ->
                    onRegister(user)

                    navController.popBackStack<LoginRoute>(
                        inclusive = false,
                    )
                }
            )
        }

        composable<MainRoute> {
//            MainScreen(
//            )
        }
    }
}
