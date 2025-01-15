import Archive.Archive
import Archive.newArchive
import Note.Note
import java.util.Scanner

class MenuLoader {

    fun downloadMenuArchives(
        onCreateArchive: (MutableMap <Int, Archive>, Int) -> Unit,
        onCreateNote:    (Archive, Int) -> Unit,
        onShowArchives:  (MutableMap <Int, Archive>) -> Unit,
        onShowNotes:     (Archive) -> Unit,
        onShowNoteInfo:  (Note) -> Unit,
        onEditNote:      (Note) -> Unit
    ) {


        val archives: MutableMap <Int, Archive> = mutableMapOf()

        val scannerMenu = Scanner(System.`in`)
        var exitNumber = archives.size + 1

        onShowArchives(archives)

        root@while (true) {
            val inPutNum: Int = getCorrectNumber(scannerMenu, exitNumber)
            when (inPutNum) {
                exitNumber -> return
                0 -> {
                    onCreateArchive(archives, exitNumber)
                    exitNumber = exitNumber + 1
                    onShowArchives(archives)
                }
                else -> {
                    val curArchive = archives.getValue(inPutNum)
                    onShowNotes(curArchive)
                    var exitNumberNotes = curArchive.notes.size + 1
                    archi@while (true) {
                        val inPutNum: Int = getCorrectNumber(scannerMenu, exitNumberNotes)
                        when (inPutNum) {
                            exitNumberNotes -> {
                                onShowArchives(archives)
                                continue@root
                            }
                            0 -> {
                                onCreateNote(curArchive, exitNumberNotes)
                                exitNumberNotes = exitNumberNotes + 1
                                onShowNotes(curArchive)
                            }
                            else -> {
                                val curNote: Note = curArchive.notes.getValue(inPutNum)!!
                                note@while (true) {
                                    onShowNoteInfo(curNote)
                                    val inPutNum: Int = getCorrectNumber(scannerMenu, 1)
                                    when (inPutNum) {
                                        1 ->  {
                                            onShowNotes(curArchive)
                                            continue@archi
                                        }
                                        0 -> {
                                            onEditNote(curNote)
                                            continue@note
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }

}