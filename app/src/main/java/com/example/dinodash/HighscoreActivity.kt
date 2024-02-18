package com.example.dinodash

import android.content.Intent
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import android.content.Context


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
    val ComicSans = FontFamily(Font(R.font.comicsans))
    val backgroundImage: Painter = painterResource(id = R.drawable.dinodashbg)
    val context = LocalContext.current

    val highscores = readHighscoresFromAssets(context, "highscore.txt")

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
            text = "Highscore",
            fontFamily = ComicSans,
            color = Color(0xFF6F7D3F),
            fontSize = 56.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(6.dp)
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

data class Highscore(val playerName: String, val score: Int) {
    val isTopThree: Boolean = false
}

@Composable
fun readHighscoresFromAssets(context: Context, fileName: String): List<Highscore> {
    val highscores = mutableListOf<Highscore>()

    try {
        val inputStream = context.assets.open(fileName)
        val reader = BufferedReader(InputStreamReader(inputStream))
        var line: String?

        // Read each line from the file and parse highscore data
        while (reader.readLine().also { line = it } != null) {
            val parts = line!!.split(" ") // Split by space
            val playerName = parts[0]
            val score = parts[1].toInt()

            // Add parsed highscore to the list
            highscores.add(Highscore(playerName, score))
        }

        // Close the reader
        reader.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }

    return highscores
}
