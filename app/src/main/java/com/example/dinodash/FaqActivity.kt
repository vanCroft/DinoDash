package com.example.dinodash

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight


@Composable
fun FaqScreen() {
    val backgroundImage: Painter = painterResource(id = R.drawable.dinodashbg)

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
            Text(
                text = "FAQ's",
                color = Color(0xFF314A22),
                fontSize = 78.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(6.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(faqList) { faq ->
                    FaqItemCard(faq)
                    Spacer(modifier = Modifier.height(10.dp))
                }
                item {
                    Button(modifier = Modifier.padding(16.dp), onClick = {
                        // Schließen der Aktivität
                    }) {
                        Text(text = "Zurück zum Hauptmenü")
                    }
                }
            }
        }
    }
}

@Composable
fun FaqItemCard(faq: FaqItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Q: ${faq.question}", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "A: ${faq.answer}", style = MaterialTheme.typography.bodyLarge)
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
        answer = "Du steuerst den Dinosaurier durch Tippen auf den Bildschirm. Ein kurzes Tippen lässt ihn springen, während langes Drücken einen höheren Sprung bewirkt."
    ),
    FaqItem(
       question = "Was passiert, wenn ich ein faules Ei berühre?",
        answer = "Wenn du ein faules Ei berührst, verlierst du Punkte."
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

@Preview(showBackground = true)
@Composable
fun FaqPreview() {
    FaqScreen()
}