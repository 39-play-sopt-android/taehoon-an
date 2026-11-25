package org.sopt.play.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.sopt.play.presentation.login.ui.LoginRoute
import org.sopt.play.presentation.main.ui.MainRoute
import org.sopt.play.presentation.register.ui.RegisterRoute
import org.sopt.play.presentation.search.SearchRoute

@Composable
fun AppNavHost() {
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
                navigateToHome = {
                    navController.navigate(MainRoute) {
                        popUpTo<LoginRoute> {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                navigateToRegister = {
                    navController.navigate(RegisterRoute) {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<RegisterRoute> {
            RegisterRoute(
                navigateToLogin = {
                    navController.popBackStack<LoginRoute>(
                        inclusive = false,
                    )
                },
            )
        }

        composable<MainRoute> {
            MainRoute(
                onSearchClick = {
                    navController.navigate(SearchRoute) {
                        launchSingleTop = true
                    }
                },
            )
        }

        composable<SearchRoute> {
            SearchRoute()
        }
    }
}
