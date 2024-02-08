package com.example.dinodash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.material3.*
import androidx.compose.ui.graphics.painter.Painter


class SettingsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Lade den aktuellen Zustand aus den SharedPreferences
        val sharedPref = getSharedPreferences("AppSettings", Context.MODE_PRIVATE)
        val isEpicPlaying = sharedPref.getBoolean("EpicMusic", true)
        val isJurassicPlaying = sharedPref.getBoolean("JurassicSound", true)

        setContent {
            SettingsScreen(
                isEpicPlaying = isEpicPlaying,
                isJurassicPlaying = isJurassicPlaying,
                onSaveSetting = { key, value ->
                    saveSetting(key, value)
                }
            )
        }
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
    onSaveSetting: (String, Boolean) -> Unit
) {
    val context = LocalContext.current
    val ComicSans = FontFamily(Font(R.font.comicsans))
    val backgroundImage: Painter = painterResource(id = R.drawable.dinodashbg)
    val homeIcon: Painter = painterResource(id = R.drawable.home)

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Hintergrundbild setzen
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
                onSaveSetting = onSaveSetting
            )
            Spacer(modifier = Modifier.height(8.dp))
            ToggleButton(
                label = "Sound",
                isOn = isJurassicPlaying,
                onSaveSetting = onSaveSetting
            )

            // Button zum Zurückkehren zum Hauptmenü
            IconButton(
                onClick = { (context as? ComponentActivity)?.finish() },
                modifier = Modifier
                    .size(128.dp) // Größe des Button
                    .padding(22.dp) // Abstand um Button von 16dp
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
    onSaveSetting: (String, Boolean) -> Unit
) {
    var isChecked by remember { mutableStateOf(isOn) }
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label)
        Switch(
            checked = isChecked,
            onCheckedChange = { newValue ->
                isChecked = newValue
                onSaveSetting(if (label == "Musik") "EpicMusic" else "JurassicSound", newValue)
            }
        )
    }
}
