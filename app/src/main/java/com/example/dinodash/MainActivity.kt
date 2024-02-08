package com.example.dinodash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import android.media.MediaPlayer
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import android.content.res.Configuration
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts


class MainActivity : ComponentActivity() {

    private lateinit var mediaPlayerJurassic: MediaPlayer
    private lateinit var mediaPlayerEpic: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StartMenuScreen()
        }

        // Initialisieren des MediaPlayers für Sound
        mediaPlayerJurassic = MediaPlayer.create(this, R.raw.jurassic)
        mediaPlayerJurassic.isLooping = true
        mediaPlayerJurassic.start()

        // Initialisieren des MediaPlayers für Music
        mediaPlayerEpic = MediaPlayer.create(this, R.raw.epic)
        mediaPlayerEpic.isLooping = true
        mediaPlayerEpic.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mediaPlayerJurassic.isPlaying) {
            mediaPlayerJurassic.stop()
        }
        mediaPlayerJurassic.release()

        if (mediaPlayerEpic.isPlaying) {
            mediaPlayerEpic.stop()
        }
        mediaPlayerEpic.release()
    }
}

@Composable
fun SettingsButton(mediaPlayerEpic: MediaPlayer, mediaPlayerJurassic: MediaPlayer) {
    val context = LocalContext.current
    val settingsLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == ComponentActivity.RESULT_OK) {
            val data: Intent? = result.data
            data?.let {
                val isEpicPlaying = it.getBooleanExtra("IS_EPIC_PLAYING", true)
                val isJurassicPlaying = it.getBooleanExtra("IS_JURASSIC_PLAYING", true)

                // Aktualisiere die MediaPlayer-Zustände basierend auf den Einstellungen
                if (isEpicPlaying) mediaPlayerEpic.start() else mediaPlayerEpic.pause()
                if (isJurassicPlaying) mediaPlayerJurassic.start() else mediaPlayerJurassic.pause()
            }
        }
    }

    Button(onClick = {
        settingsLauncher.launch(Intent(context, SettingsActivity::class.java).apply {
            // Übermittle die aktuellen Zustände der Mediaplayer als Extras, falls notwendig
            putExtra("IS_EPIC_PLAYING", mediaPlayerEpic.isPlaying)
            putExtra("IS_JURASSIC_PLAYING", mediaPlayerJurassic.isPlaying)
        })
    }) {
        Text("Einstellungen")
    }
}



@Composable
fun StartMenuScreen() {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val padding = if (isLandscape) 16.dp else 44.dp
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val fontScale = if (isLandscape) 0.7 else 1.0 // Skalierung der Schriftgröße basierend auf der Orientierung

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFA9B489))
    ) {
        Image(
            painter = painterResource(id = R.drawable.dinodashbg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = screenHeight * 0.1f, start = padding, end = padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "DINO DASH",
                fontFamily = FontFamily(Font(R.font.comicsans)),
                fontSize = (120 * fontScale).sp,
                color = Color(0xFF6F7D3F),
                modifier = Modifier.padding(16.dp)
            )

            StartButton(screenWidth)

            // Zeile für Buttons Highscore, FAQ und Einstellungen
            Row(
                modifier = Modifier.padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                MenuButton(R.drawable.highscore, screenWidth) {
                    context.startActivity(Intent(context, HighscoreActivity::class.java))
                }
                MenuButton(R.drawable.faq, screenWidth) {
                    context.startActivity(Intent(context, FaqActivity::class.java))
                }
                MenuButton(R.drawable.settings, screenWidth) {
                    context.startActivity(Intent(context, SettingsActivity::class.java))
                }
            }
        }
    }
}


@Composable
fun StartButton(screenWidth: Dp) {
    val context = LocalContext.current

    Button(
        onClick = {
            val intent = Intent(context, GameActivity::class.java)
            context.startActivity(intent)
        },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF314A22)),
        modifier = Modifier
            .padding(16.dp)
            .height(80.dp)
            .width(200.dp) // Breite
    ) {
        Text(
            text = "START",
            color = Color.White,
            fontSize = 26.sp
        )
    }
}

@Composable
fun MenuButton(imageResource: Int, screenWidth: Dp, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .width(screenWidth * 0.25f) // Dynamische Breite basierend auf Bildschirmbreite
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = null, // Beschreibung für Barrierefreiheit
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StartMenuScreen ()
}

