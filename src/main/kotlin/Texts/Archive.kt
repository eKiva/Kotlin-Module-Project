package Archive

import Note.Note

class Archive (val name: String, val notes: MutableMap<Int, Note?>)


public fun newArchive(name: String): Archive {
    var notes: MutableMap<Int, Note?> = mutableMapOf()
    return Archive(name,notes)
}

public fun getScreenArchives(archives: MutableMap <Int, Archive>) {
    println("* АРХИВЫ *")
    println("0. Создать архив")
    archives.forEach{ println ("${it.key}. ${it.value.name}")}
    println ("${archives.size + 1}. Выход")
}
