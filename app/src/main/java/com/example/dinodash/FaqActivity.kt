package com.example.dinodash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.runtime.Composable


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
    val ComicSans = FontFamily(Font(R.font.comicsans))
    val backgroundImage: Painter = painterResource(id = R.drawable.dinodashbg)
    val homeIcon: Painter = painterResource(id = R.drawable.home)
    val context = LocalContext.current

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
        ){
            Text(
                text = "FAQs",
                fontFamily = ComicSans,
                color = Color(0xFF6F7D3F),
                fontSize = 56.sp,
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
                    }
                }

            }
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

@Composable
fun FaqItemCard(faq: FaqItem) {
    var expandedState by remember { mutableStateOf(false) }
    val transitionState = remember { MutableTransitionState(expandedState) }
    transitionState.targetState = expandedState

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable { expandedState = !expandedState }
                .padding(16.dp)
        ) {
            Text(
                text = faq.question,
                style = MaterialTheme.typography.headlineSmall,
                color = Color(0xFF314A22),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )

            AnimatedVisibility(
                visibleState = transitionState,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Text(
                    text = faq.answer,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}


// FAQ
data class FaqItem(val question: String, val answer: String)

val faqList = listOf(
    FaqItem(
        question = "Wie beginne ich das Spiel?",
        answer = "Das Spiel startet, wenn du auf den „START“-Button im Hauptmenü tippst. Das Spiel ist für Touch-Geräte optimiert, also benutze einfach deinen Finger, um die Befehle zu geben"
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