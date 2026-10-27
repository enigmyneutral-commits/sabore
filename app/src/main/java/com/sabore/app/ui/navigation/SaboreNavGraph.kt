package com.sabore.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sabore.app.ui.screens.*

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("home")
    object Explore : Screen("explore")
    object Cookbook : Screen("cookbook")
    object Community : Screen("community")
    object Profile : Screen("profile")
    object AddRecipe : Screen("add_recipe")
    object ProSubscription : Screen("pro_subscription")
    object Help : Screen("help")
    object Notifications : Screen("notifications")
    object AssistantChat : Screen("assistant_chat")
    object RecipeDetail : Screen("recipe_detail/{recipeId}") {
        fun createRoute(recipeId: String) = "recipe_detail/$recipeId"
    }
    object CookingMode : Screen("cooking_mode/{recipeId}") {
        fun createRoute(recipeId: String) = "cooking_mode/$recipeId"
    }
}

@Composable
fun SaboreNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = Screen.Login.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                navController = navController
            )
        }
        composable(Screen.Explore.route) {
            ExploreScreen(
                navController = navController
            )
        }
        composable(Screen.Cookbook.route) {
            MyCookbookScreen(
                navController = navController
            )
        }
        composable(Screen.Community.route) {
            CommunityScreen(
                navController = navController
            )
        }
        composable(Screen.Profile.route) {
            ProfileScreen(
                navController = navController
            )
        }
        composable(Screen.AddRecipe.route) {
            AddRecipeScreen(
                onRecipeSaved = {
                    navController.popBackStack()
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(Screen.ProSubscription.route) {
            ProSubscriptionScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(Screen.Help.route) {
            HelpScreen(navController = navController)
        }
        composable(Screen.Notifications.route) {
            NotificationsScreen(navController = navController)
        }
        composable(Screen.AssistantChat.route) {
            AssistantChatScreen(navController = navController)
        }
        composable(
            route = Screen.RecipeDetail.route,
            arguments = listOf(navArgument("recipeId") { type = NavType.StringType })
        ) { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getString("recipeId") ?: ""
            RecipeDetailScreen(
                recipeId = recipeId,
                navController = navController,
                onStartCooking = { id ->
                    navController.navigate(Screen.CookingMode.createRoute(id))
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = Screen.CookingMode.route,
            arguments = listOf(navArgument("recipeId") { type = NavType.StringType })
        ) { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getString("recipeId") ?: ""
            CookingModeScreen(
                recipeId = recipeId,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
