package Note

import Archive.Archive

class Note (val name: String, var text: String)

public fun createNote(name: String, text: String): Note {
    return Note(name, text)
}


public fun getScreenNotes(archive:  Archive) {
    println("* ЗАМЕТКИ АРХИВА ${archive.name} *")
    printPointOfMenu(number = 0, text = "Создать заметку")
    for (note in archive.notes) {
        printPointOfMenu(note.key, note.value!!.name)
    }
    printPointOfMenu(number = archive.notes.size + 1, text = "Выход")
}

public fun getFullNote(note: Note) {
    println("* ЗАМЕТКА ${note.name} *")
    printPointOfMenu(number = 0, text = "Просмотреть заметку")
    printPointOfMenu(number = 1, text = "Изменить заметку")
    printPointOfMenu(number = 2, text = "Выход")
}

public fun printPointOfMenu(number: Int, text: String) { println ("${number}. $text") }