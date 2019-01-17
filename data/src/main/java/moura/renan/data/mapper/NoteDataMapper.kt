package moura.renan.data.mapper

import moura.renan.data.model.Note
import moura.renan.domain.model.NoteDomain

class NoteDataMapper : EntityMapper<Note, NoteDomain> {
    override fun mapFromEntity(entity: Note): NoteDomain {
        return NoteDomain(entity.id, entity.title, entity.description, entity.priority)
    }

    override fun mapToEntity(domain: NoteDomain): Note {
        return Note(domain.id, domain.title, domain.description, domain.priority)
    }
}