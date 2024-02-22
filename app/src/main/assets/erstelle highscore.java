import java.io.File

fun main() {
    // Pfad zur Datei highscore.txt im assets-Verzeichnis des Projekts
    val filePath = "app/src/main/assets/highscore.txt"

    // Schreibe die Highscore-Werte in die Datei
    val highscores = listOf(
        "Steffen 1150",
        "Dirk 450",
        "Max 800",
        "Uschi 560"
    )

    // Versuche, die Datei zu schreiben
    try {
        val file = File(filePath)
        file.createNewFile() // Erstellt die Datei, wenn sie nicht vorhanden ist
        file.writeText(highscores.joinToString("\n")) // Schreibt die Highscores in die Datei
        println("Die Datei highscore.txt wurde erfolgreich erstellt und aktualisiert.")
    } catch (e: Exception) {
        println("Fehler beim Erstellen der Datei highscore.txt: ${e.message}")
    }
}