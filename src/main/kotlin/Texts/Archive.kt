package Archive

import Note.Note

class Archive (val name: String, val notes: MutableMap<Int, Note?>)


public fun createArchive(name: String): Archive {
    var notes: MutableMap<Int, Note?> = mutableMapOf()
    return Archive(name,notes)
}

public fun getScreenArchives(archives: MutableMap <Int, Archive>) {
    println("* АРХИВЫ *")
    println("0. Создать архив")
    for (archive in archives) {
        println ("${archive.key}. ${archive.value.name}")
    }
    println ("${archives.size + 1}. Выход")
}
