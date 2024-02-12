package com.example.dinodash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import android.media.MediaPlayer

class SettingsActivity : ComponentActivity() {

    private lateinit var mediaPlayerEpic: MediaPlayer
    private lateinit var mediaPlayerJurassic: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialisiere die Media Player für Musik und Sound
        mediaPlayerEpic = MediaPlayer.create(this, R.raw.epic)
        mediaPlayerJurassic = MediaPlayer.create(this, R.raw.jurassic)

        // Lade den aktuellen Zustand aus den SharedPreferences
        val sharedPref = getSharedPreferences("AppSettings", Context.MODE_PRIVATE)
        val isEpicPlaying = sharedPref.getBoolean("EpicMusic", false)
        val isJurassicPlaying = sharedPref.getBoolean("JurassicSound", false)

        setContent {
            SettingsScreen(
                isEpicPlaying = isEpicPlaying,
                isJurassicPlaying = isJurassicPlaying,
                onSaveSetting = { key, value ->
                    saveSetting(key, value)
                },
                mediaPlayerEpic = mediaPlayerEpic,
                mediaPlayerJurassic = mediaPlayerJurassic
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayerEpic.release()
        mediaPlayerJurassic.release()
    }

    private fun saveSetting(key: String, value: Boolean) {
        val sharedPref = getSharedPreferences("AppSettings", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putBoolean(key, value)
            apply()
        }
    }
}

@Composable
fun SettingsScreen(
    isEpicPlaying: Boolean,
    isJurassicPlaying: Boolean,
    onSaveSetting: (String, Boolean) -> Unit,
    mediaPlayerEpic: MediaPlayer,
    mediaPlayerJurassic: MediaPlayer
) {
    val context = LocalContext.current
    val ComicSans = FontFamily(Font(R.font.comicsans))
    val backgroundImage = painterResource(id = R.drawable.dinodashbg)
    val homeIcon = painterResource(id = R.drawable.home)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Setze das Hintergrundbild
        Image(
            painter = backgroundImage,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Einstellungen",
                fontFamily = ComicSans,
                color = Color(0xFF6F7D3F),
                fontSize = 56.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(6.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            ToggleButton(
                label = "Musik",
                isOn = isEpicPlaying,
                onSaveSetting = onSaveSetting,
                mediaPlayer = mediaPlayerEpic
            )
            Spacer(modifier = Modifier.height(8.dp))
            ToggleButton(
                label = "Sound",
                isOn = isJurassicPlaying,
                onSaveSetting = onSaveSetting,
                mediaPlayer = mediaPlayerJurassic
            )

            // Button zum Zurückkehren zum Hauptmenü
            IconButton(
                onClick = {
                    (context as? ComponentActivity)?.apply {
                        setResult(
                            -1, Intent().apply {
                                putExtra("IS_EPIC_PLAYING", isEpicPlaying)
                                putExtra("IS_JURASSIC_PLAYING", isJurassicPlaying)
                            }
                        )
                        finish()
                    }
                },
                modifier = Modifier
                    .size(128.dp)
                    .padding(22.dp)
            ) {
                Image(
                    painter = homeIcon,
                    contentDescription = "Zurück zum Hauptmenü",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun ToggleButton(
    label: String,
    isOn: Boolean,
    onSaveSetting: (String, Boolean) -> Unit,
    mediaPlayer: MediaPlayer
) {
    var isChecked by remember { mutableStateOf(isOn) }

    val ComicSans = FontFamily(Font(R.font.comicsans))

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = label,
            fontFamily = ComicSans,
            modifier = Modifier.padding(end = 8.dp)
        )
        Switch(
            checked = isChecked,
            onCheckedChange = { newValue ->
                isChecked = newValue
                onSaveSetting(if (label == "Musik") "EpicMusic" else "JurassicSound", newValue)
                if (newValue) {
                    if (!mediaPlayer.isPlaying) {
                        mediaPlayer.start()
                    }
                } else {
                    if (mediaPlayer.isPlaying) {
                        mediaPlayer.pause()
                        mediaPlayer.seekTo(0)
                    }
                }
            },
            colors = SwitchDefaults.colors( // Hier wird die Hintergrundfarbe des Schalters festgelegt
                checkedThumbColor = Color(0xFF6F7D3F),
                uncheckedThumbColor = Color.Gray
            )
        )
    }
}
