package com.example.dinodash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import android.content.Intent
import androidx.compose.ui.platform.LocalContext
import com.example.dinodash.ui.theme.DinoDashTheme
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DinoDashTheme {
                // A surface container using a custom background color
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF99cc99) // Custom background color
                ) {
                    MainMenu()
                }
            }
        }
    }
}

@Composable
fun MainMenu() {
    val context = LocalContext.current

    // Custom button colors
    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = Color(0xFF314A22), // Button background color
        contentColor = Color.White // Text color
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
                // Intent, um GameActivity zu starten
                val intent = Intent(context, GameActivity::class.java)
                context.startActivity(intent)
            },
            colors = buttonColors // Apply custom button colors
        ) {
            Text(text = "DinoDash starten", style = TextStyle(
                //fontFamily = FontFamily(Font(R.font.comic_sans)), // Verwendung von Comic Sans
                fontWeight = FontWeight.Bold, // Fette Schrift
                fontSize = 16.sp // Optional: Schriftgröße anpassen
            ))
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = { /* TODO: Highscore Logic */ }, colors = buttonColors) {
            Text(text = "Highscore")
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = {
            val intent = Intent(context, FaqActivity::class.java)
            context.startActivity(intent)
        }, colors = buttonColors) {
            Text(text = "FAQ")
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = {
            context.startActivity(Intent(context, SettingsActivity::class.java))
        }, colors = buttonColors) {
            Text(text = "Einstellungen")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DinoDashTheme {
        MainMenu()
    }
}