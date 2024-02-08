package com.example.dinodash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.content.Intent
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

class HighscoreActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HighscoreScreen()
        }
    }
}

@Composable
fun HighscoreScreen() {
    val backgroundImage: Painter = painterResource(id = R.drawable.dinodashbg)
    val context = LocalContext.current

    // Beispiel-Highscores
    val highscores = listOf(
        Highscore("Spieler 1", 1200),
        Highscore("Spieler 2", 900),
        Highscore("Spieler 3", 800),
        Highscore("Spieler 4", 750),
        Highscore("Spieler 5", 700)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = backgroundImage,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "Highscore", // Überschrift "Highscore" hinzufügen
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            items(highscores) { highscore ->
                HighscoreItem(highscore)
            }
        }

        // Button zum Zurückkehren zum Hauptmenü
        Button(
            onClick = {
                context.startActivity(Intent(context, MainActivity::class.java))
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Zurück zum Hauptmenü")
        }
    }
}

@Composable
fun HighscoreItem(highscore: Highscore) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(if (highscore.isTopThree) Color.Green else Color.Transparent)
        ) {
            Text(
                text = highscore.playerName,
                style = MaterialTheme.typography.headlineSmall,
                color = Color(0xFF314A22),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = "Punktzahl: ${highscore.score}",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 16.sp,
                fontWeight = FontWeight.Light
            )
        }
    }
}

// Datenklasse für Highscore
data class Highscore(val playerName: String, val score: Int) {
    val isTopThree: Boolean = false // Setzen Sie dies auf true, wenn es sich um einen der Top 3 Highscores handelt
}
