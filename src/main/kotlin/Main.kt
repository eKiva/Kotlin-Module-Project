import Archive.Archive
import Archive.createArchive
import Archive.getScreenArchives
import Note.Note
import Note.createNote
import Note.getFullNote
import Note.getScreenNotes
import java.util.Scanner



fun main() {

    val archives: MutableMap <Int, Archive> = mutableMapOf()

    startNotes(archives)

}

fun startNotes(archives: MutableMap<Int, Archive>) {

    getScreenArchives(archives)

    val scannerMenu = Scanner(System.`in`)
    var weAreDo: Boolean = true

    var exitNumber = archives.size + 1

    while (weAreDo) {

        val inPutNum: Int = getCorrectNumber(scannerMenu, exitNumber)

        when (inPutNum) {
            exitNumber -> return // выходим из приложения
            0 -> { //Создание архива
                var weCreated: Boolean = false
                while (weCreated == false) {
                    println("Введите название архива")

                    val inPutName: String = getCorrectString(scannerMenu)
                    //Создаем архив
                    archives.set(exitNumber, createArchive(inPutName))
                    exitNumber = exitNumber + 1

                    getScreenArchives(archives)
                    weCreated = true

                }
            }

            else -> {// заходим в архив, выведем заметки

                val curArchive: Archive = archives.getValue(inPutNum)
                getScreenNotes(curArchive)

                val weAreDo_Notes: Boolean = true
                var exitNumber_Notes = curArchive.notes.size + 1

                while (weAreDo_Notes) {
                    val inPutNum: Int = getCorrectNumber(scannerMenu, exitNumber_Notes+1)
                    var weWantGoBack: Boolean = false

                    when (inPutNum) {
                        exitNumber_Notes -> {startNotes(archives); return}  // выходим обратно в Архивы
                        exitNumber_Notes+1 -> {weAreDo = false; return}  // выходим из программы
                        0 -> { //Создание заметки
                            var weCreatedNote: Boolean = false
                            weWantGoBack = false
                            while (weCreatedNote == false) {
                                println("Введите название заметки")
                                val inPutName: String = getCorrectString(scannerMenu)
                                println("Введите текст заметки")
                                val inPutText: String = getCorrectString(scannerMenu)

                                curArchive.notes.set(exitNumber_Notes, createNote(inPutName, inPutText))
                                exitNumber_Notes = exitNumber_Notes + 1
                                getScreenNotes(curArchive)
                                weCreatedNote = true
                            }
                        }

                        else -> {// заходим в заметочку
                            val weAreDo_ViewNote: Boolean = true

                            val curNote: Note = curArchive.notes.getValue(inPutNum)!!

                            while (weAreDo_ViewNote && !weWantGoBack) {
                                getFullNote(curNote)

                                val inPutNum: Int = getCorrectNumber(scannerMenu, 3)

                                when (inPutNum) {
                                    0 -> {
                                        println("Заметка: ${curNote.text}")
                                    } //Просмотреть
                                    1 -> {
                                        println("Заметка: ${curNote.text}")
                                        println("Введите текст заметки:")
                                        val inPutText: String = getCorrectString(scannerMenu)
                                        curNote.text = inPutText
                                    } //Изменить текст
                                    2 -> {

                                        //val curArchive: Archive = archives.getValue(inPutNum)
                                        getScreenNotes(curArchive)

                                        //weAreDo_Notes: Boolean = true
                                        exitNumber_Notes = curArchive.notes.size + 1
                                        weWantGoBack = true
                                    }                                    } //Выход обратно к списку заметок
                                }
                        }
                    }
                }
            }
        }
    }
}





fun isNum(input: String, exitMax: Int): Boolean { return input.toIntOrNull() != null && input.toInt()<= exitMax && input.toInt() >= 0}

fun isNotNull(input: String): Boolean { return input.toString() != ""}

fun getCorrectString(scanner: Scanner): String {
    var stringIsCorrect: Boolean = false
    var resultString:    String = ""

    while(!stringIsCorrect) {resultString = scanner.nextLine().toString(); if (isNotNull(resultString)) stringIsCorrect = true}
    return resultString
}

fun getCorrectNumber(scanner: Scanner, exitMax: Int): Int {
    var numberIsCorrect: Boolean = false
    var resultString:    String = ""
    var resultNumber:    Int = 0

    while(!numberIsCorrect) {
        resultString = scanner.nextLine().toString()
        if (isNum(resultString, exitMax)) {
            resultNumber = resultString.toInt(); numberIsCorrect = true
        } else {
            println("Некорректный ввод. Введите число от 0 до $exitMax")}
    }
    return resultNumber
}
