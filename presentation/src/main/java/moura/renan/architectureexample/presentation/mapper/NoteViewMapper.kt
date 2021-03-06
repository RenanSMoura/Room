package moura.renan.architectureexample.presentation.mapper

import moura.renan.domain.model.NoteDomain
import moura.renan.architectureexample.presentation.model.NoteView

class NoteViewMapper : EntityMapper<NoteView, NoteDomain> {
    override fun mapFromEntity(entity: NoteView): NoteDomain {
        return NoteDomain(entity.id, entity.title, entity.description, entity.priority.toInt())
    }

    override fun mapToEntity(domain: NoteDomain): NoteView {
        return NoteView(domain.id, domain.title, domain.description, domain.priority.toFloat())

    }
}