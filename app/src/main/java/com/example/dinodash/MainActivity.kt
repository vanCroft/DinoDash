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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import com.example.dinodash.ui.theme.DinoDashTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DinoDashTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            // Intent, um GameActivity zu starten
            val intent = Intent(context, GameActivity::class.java)
            context.startActivity(intent)
        }) {
            Text(text = "DinoDash starten")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* TODO: Highscore Logic */ }) {
            Text(text = "Highscore")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
        val intent = Intent(context, FaqActivity::class.java)
        context.startActivity(intent)
    }) {
        Text(text = "FAQ")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            context.startActivity(Intent(context, SettingsActivity::class.java))
           }) {
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