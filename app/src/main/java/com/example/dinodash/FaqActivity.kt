package com.example.dinodash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.ui.platform.LocalContext


class FaqActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FaqScreen()
        }
    }
}

@Composable
fun FaqScreen() {
    val activity = LocalContext.current as? ComponentActivity

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(faqList) { faq ->
            Text(text = "Q: ${faq.question}", style = MaterialTheme.typography.headlineMedium)
            Text(text = "A: ${faq.answer}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            Button(modifier = Modifier.padding(16.dp), onClick = {
                activity?.finish()
            }) {
                Text(text = "Zurück zum Hauptmenü")
            }
        }
    }
}

// FAQ
data class FaqItem(val question: String, val answer: String)

val faqList = listOf(
    FaqItem(
        question = "Wie beginne ich das Spiel?",
        answer = "Das Spiel startet, wenn du auf den „DinoDash starten“-Button im Hauptmenü tippst. Das Spiel ist für Touch-Geräte optimiert, also benutze einfach deinen Finger, um die Befehle zu geben"
    ),
    FaqItem(
        question = "Was ist das Ziel des Spiels?",
        answer = "Dein Ziel ist es, so viele Eier wie möglich zu sammeln, um Punkte zu erzielen. Aber Achtung, es gibt auch faule Eier! Diese bringen Punktabzug."
    ),
    FaqItem(
        question = "Wie steuere ich den Dinosaurier?",
        answer = "Du steuerst den Dinosaurier durch Tippen auf den Bildschirm. Ein kurzes Tippen lässt ihn springen, während langes Drücken einen höheren Sprung bewirkt. Mit Wischbewegungen kannst du ihm helfen, Hindernissen auszuweichen."
    ),
    FaqItem(
       question = "Was passiert, wenn ich ein faules Ei berühre?",
        answer = "Wenn du ein faules Ei berührst, verlierst du Punkte. Die Anzahl der verlorenen Punkte hängt davon ab, wie viele gute Eier du bereits gesammelt hast."
    ),
    FaqItem(
        question = "Wie endet ein Level?",
        answer = "Ein Level endet, wenn du das Ende der Strecke erreichst oder wenn du zu viele Punkte durch faule Eier verloren hast."
    ),
    FaqItem(
        question = "Kann ich das Spiel auf meinem Tablet spielen?",
        answer = "Ja, das Spiel ist für Touch-Geräte wie Smartphones und Tablets optimiert."
    ),
)
