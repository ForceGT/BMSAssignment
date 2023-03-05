package dev.gtxtreme.bmsassignment.ui.screens.movie_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.gtxtreme.bmsassignment.ui.result.UiResult
import dev.gtxtreme.bmsassignment.ui.viewmodel.MovieDetailViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieDetailScreen(onNavigateToBooking: () -> Unit) {

    val viewModel = getViewModel<MovieDetailViewModel>()
    val state = viewModel.movieDetailFlow.collectAsState()

    Box(Modifier.fillMaxSize()) {
        LazyColumn(
            Modifier
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            when (val result = state.value) {
                is UiResult.Error -> {
                    item {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text(result.error, modifier = Modifier.align(Alignment.Center))
                            Spacer(modifier = Modifier.height(10.dp))
                            Button(onClick = viewModel::getMovieDetails) {
                                Text(text = "Retry")
                            }
                        }
                    }

                }
                is UiResult.Success -> {
                    val movieDetails = result.data
                    item {
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(24.dp)),
                            model = movieDetails.backdropImageUrl,
                            contentDescription = "Backdrop Image"
                        )
                    }
                    item {
                        Text(text = movieDetails.movieTitle, style = MaterialTheme.typography.h4)
                    }
                    item {
                        Text(
                            text = movieDetails.movieOverview,
                            style = MaterialTheme.typography.subtitle2
                        )
                    }
                    item {
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            items(movieDetails.genres) {
                                Box(
                                    Modifier
                                        .background(
                                            ChipDefaults
                                                .chipColors()
                                                .backgroundColor(
                                                    enabled = true
                                                ).value,
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                        .padding(8.dp)
                                ) {
                                    Text(text = it)
                                }
                            }
                        }
                    }
                    item {
                        Text(text = "Crew", style = MaterialTheme.typography.h6)
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(movieDetails.movieCrew) {
                                val (name, profilePath) = it
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    AsyncImage(
                                        modifier = Modifier
                                            .height(180.dp)
                                            .clip(RoundedCornerShape(12.dp)),
                                        model = profilePath,
                                        contentDescription = "crew detail image"
                                    )
                                    Text(text = name)
                                }

                            }
                        }
                    }
                    item {
                        Text(text = "Cast", style = MaterialTheme.typography.h6)
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(movieDetails.movieCast) {
                                val (name, profilePath) = it
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    AsyncImage(
                                        modifier = Modifier
                                            .height(180.dp)
                                            .clip(RoundedCornerShape(12.dp)),
                                        model = profilePath,
                                        contentDescription = "cast detail image"
                                    )
                                    Text(text = name)
                                }

                            }
                        }
                    }
                    // Adding extra spacer for better scrolling and preventing clipping
                    // at the bottom if any
                    item {
                        Spacer(modifier = Modifier.height(80.dp))
                    }

                }
                UiResult.Empty,
                UiResult.Loading -> {
                    item {
                        Box(modifier = Modifier.fillMaxSize()) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                    }
                }
            }
        }
        BookTicketsButton(onNavigateToBooking)
    }

}


@Composable
private fun BoxScope.BookTicketsButton(onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .align(Alignment.BottomStart)
            .fillMaxWidth(),
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier.padding(16.dp),
        ) {
            Text(
                text = "Book tickets",
                color = MaterialTheme.colors.onPrimary
            )
        }
    }
}