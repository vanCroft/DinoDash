package com.example.dinodash


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

class GameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameScreen()
        }
    }
}

@Composable
fun GameScreen() {
    // Hier kommt die Spiellogik rein
    Text(text = "Hier beginnt das Spiel!")
}
