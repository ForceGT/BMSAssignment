package dev.gtxtreme.bmsassignment.ui.screens.booking_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun BookingDetailScreen() {

    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Booking Detail Screen", modifier = Modifier.align(Alignment.Center))
    }
}