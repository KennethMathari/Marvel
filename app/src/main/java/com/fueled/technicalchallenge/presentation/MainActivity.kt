package com.fueled.technicalchallenge.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.fueled.technicalchallenge.presentation.ui.theme.TechnicalChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TechnicalChallengeTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
//                    val navController = rememberNavController()
//                    NavHost(
//                        navController = navController,
//                        startDestination = Screen.CharacterListScreen.route
//                    ) {
//                        composable(
//                            route = Screen.CharacterListScreen.route
//                        ) {
//                            CharacterListScreen()
//                        }
//                    }
                    App()
                }
            }
        }
    }
}