package moura.renan.architectureexample.domain.usecase.features.notes

import io.reactivex.Completable
import moura.renan.architectureexample.domain.repository.NotesRepository
import moura.renan.architectureexample.domain.usecase.base.BaseCompletableUseCase

class DeleteAllNotes(private val notesRepository: NotesRepository) : BaseCompletableUseCase<Unit>() {
    override fun buildUseCaseCompletable(param: Unit?): Completable {
       return notesRepository.deleteAllNotes()
    }
}