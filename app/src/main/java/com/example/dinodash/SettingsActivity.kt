package com.example.dinodash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.Button



class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SettingsScreen()
        }
    }
    }

@Composable
fun SettingsScreen() {
    var volume by remember { mutableStateOf(0.5f) }
    val activity = LocalContext.current as? ComponentActivity

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Lautstärke: ${(volume * 100).toInt()}%")
        Slider(
            value = volume,
            onValueChange = { volume = it },
            onValueChangeFinished = {
                // Lautstärkeänderung
            }
        )
        Button(onClick = {
            activity?.finish()
        }) {
            Text(text = "Zurück zum Hauptmenü")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsPreview() {
    SettingsScreen()
}
