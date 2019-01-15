package moura.renan.architectureexample.domain.usecase.features.notes

import io.reactivex.Completable
import moura.renan.architectureexample.domain.model.NoteDomain
import moura.renan.architectureexample.domain.repository.NotesRepository
import moura.renan.architectureexample.domain.usecase.base.BaseCompletableUseCase
import java.lang.UnsupportedOperationException

class DeleteNote(private val notesRepository: NotesRepository) : BaseCompletableUseCase<NoteDomain>() {
    override fun buildUseCaseCompletable(param: NoteDomain?): Completable {
        if (param == null) throw UnsupportedOperationException("Note Cant be null")
        return notesRepository.deleteNote(param)
    }
}