package dev.gtxtreme.bmsassignment.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.gtxtreme.bmsassignment.ui.screens.booking_screen.BookingDetailScreen
import dev.gtxtreme.bmsassignment.ui.screens.movie_detail.MovieDetailScreen
import dev.gtxtreme.bmsassignment.ui.theme.BMSAssignmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BMSAssignmentTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "movie_detail"
                    ) {
                        composable("movie_detail") {
                            MovieDetailScreen{
                                navController.navigate("booking_screen")
                            }
                        }
                        composable("booking_screen") {
                            BookingDetailScreen()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BMSAssignmentTheme {
        Greeting("Android")
    }
}