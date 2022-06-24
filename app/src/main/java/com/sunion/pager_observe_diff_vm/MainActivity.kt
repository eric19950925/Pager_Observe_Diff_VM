package com.sunion.pager_observe_diff_vm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavigationComponent(
                navController,
//                onLogoutClick = this::goLogin,
//                onLoginSuccess = this::goHome
            )
        }
    }
}

@Composable
fun NavigationComponent(navController: NavHostController/*, onLogoutClick: () -> Unit, onLoginSuccess: () -> Unit*/) {
    val homeViewModel = viewModel<HomeViewModel>()
    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {
        composable("welcome") {
            HomeScreen(
                homeViewModel,
            )
        }
    }
}