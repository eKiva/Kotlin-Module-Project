package Note

import Archive.Archive

class Note (val name: String, var text: String)

public fun newNote(name: String, text: String): Note {
    return Note(name, text)
}


public fun getScreenNotes(archive:  Archive) {
    println("* ЗАМЕТКИ АРХИВА ${archive.name} *")
    println("0. Создать заметку")
    archive.notes.forEach{ println ("${it.key}. ${it.value!!.name}")}
    println ("${archive.notes.size + 1}. Выход (Вернуться к списку архивов)")
}


public fun getScreenNoteInfo(note: Note) {
    println("* ЗАМЕТКА ${note.name} *")
    println("Текст заметки: ${note.text}")
    println("0. Изменить текст заметки")
    println("1. Выход (Вернуться к списку заметок архива)")
}

