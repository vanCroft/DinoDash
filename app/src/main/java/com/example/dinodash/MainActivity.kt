package com.example.dinodash

import android.content.Intent
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StartMenuScreen()
        }
    }
}

@Composable
fun StartMenuScreen() {
    // Hintergrundbild + Logo
    val backgroundImage: Painter = painterResource(id = R.drawable.dinodashbg)
    val logoImage: Painter = painterResource(id = R.drawable.dinodashlogo)
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFA9B489))
    ) {
        Image(
            painter = backgroundImage,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 123.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = logoImage,
                contentDescription = "Dino Dash Logo"
            )

            StartButton()

            // Zeile für zusätzliche Buttons
            Row(
                modifier = Modifier.padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { /* Highscore Logik */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF314A22)),
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Text("Highscore")
                }
                Button(
                    onClick = {
                        // Öffnen der FAQ's
                        context.startActivity(Intent(context, FaqActivity::class.java))
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF314A22)),
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Text("FAQ's")
                }
                Button(
                    onClick = { /* Einstellungen Logik */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF314A22)),
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Text("Einstellungen")
                }
            }
        }
    }
}

@Composable
fun StartButton() {
    // Zugriff auf den aktuellen Context
    val context = LocalContext.current

    Button(
        onClick = {
            //Starten des Spiels
            val intent = Intent(context, GameActivity::class.java)
            context.startActivity(intent)
        },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF314A22)),
        modifier = Modifier
            .padding(16.dp)
            .height(80.dp)
    ) {
        Text(
            text = "DinoDash STARTEN",
            color = Color.White,
            fontSize = 26.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StartMenuScreen ()
}