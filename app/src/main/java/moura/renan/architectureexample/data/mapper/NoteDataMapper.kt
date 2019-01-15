package moura.renan.architectureexample.data.mapper

import moura.renan.architectureexample.data.model.Note
import moura.renan.architectureexample.domain.model.NoteDomain

class NoteDataMapper : EntityMapper<Note, NoteDomain> {
    override fun mapFromEntity(entity: Note): NoteDomain {
        return NoteDomain(entity.id, entity.title, entity.description, entity.priority)
    }

    override fun mapToEntity(domain: NoteDomain): Note {
        return Note(domain.id, domain.title, domain.description, domain.priority)
    }
}