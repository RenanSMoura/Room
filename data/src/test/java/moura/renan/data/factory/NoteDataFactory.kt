package moura.renan.data.factory

import moura.renan.data.model.Note
import moura.renan.domain.model.NoteDomain

object NoteDataFactory {


    fun createNoteList(number : Int) : List<NoteDomain> {
        return (0..number).map { createNote() }
    }

    fun createNote() : NoteDomain{
        return NoteDomain(DataFactory.randomInt(),DataFactory.randomUuid(),DataFactory.randomUuid(),DataFactory.randomInt())
    }
    fun createEntityNote() : Note{
        return Note(DataFactory.randomInt(),DataFactory.randomUuid(),DataFactory.randomUuid(),DataFactory.randomInt())
    }
}