package moura.renan.domain.usecase.features.notes.factory

import moura.renan.domain.model.NoteDomain

object NoteDataFactory {
    fun createNoteList(number : Int) : List<NoteDomain> {
        return (0..number).map { createNote() }
    }

    fun createNote() : NoteDomain {
        return NoteDomain(DataFactory.randomInt(),DataFactory.randomUuid(),DataFactory.randomUuid(),DataFactory.randomInt())
    }

}