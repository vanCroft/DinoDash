package com.example.dinodash.ui.screens

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.dinodash.R
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class HighscoreActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HighscoreScreen(this)
        }
    }

    fun navigateToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}

@Composable
fun HighscoreScreen(activity: HighscoreActivity) {
    val backgroundImage: Painter = painterResource(id = R.drawable.dinodashbg)
    val homeIcon = painterResource(id = R.drawable.home)
    val highscores = generateHighscores(activity)
    val ComicSans = FontFamily(Font(R.font.comicsans))

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = backgroundImage,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(44.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = homeIcon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(76.dp)
                        .clickable { activity.navigateToMainActivity() },
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Highscore",
                    fontFamily = ComicSans,
                    color = Color(0xFF6F7D3F),
                    fontSize = 56.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(6.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(highscores) { highscore ->
                    HighscoreItem(highscore)
                }
            }
        }
    }
}
//Anzeige der Highscoreeinträge
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
                text = "Punktzahl: ${highscore.score}",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 16.sp,
                fontWeight = FontWeight.Light
            )
        }
    }
}

data class Highscore(val score: Int) {
    val isTopThree: Boolean = false
}

//Highscore aus Datei highscores.txt lesen
fun generateHighscores(activity: HighscoreActivity): List<Highscore> {
    val highscores = mutableListOf<Highscore>()
    val context = activity.applicationContext

    try {
        val inputStream = context.assets.open("highscores.txt")
        val reader = BufferedReader(InputStreamReader(inputStream))
        var line: String?

        // Datei auslesen und Zeile für Zeile parsen
        while (reader.readLine().also { line = it } != null) {
            val score = line!!.toInt()
            highscores.add(Highscore(score))
        }

        // Reader schließen
        reader.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }

    // Sortiere Highscore in absteigender Reihenfolge
    return highscores.sortedByDescending { it.score }
}
