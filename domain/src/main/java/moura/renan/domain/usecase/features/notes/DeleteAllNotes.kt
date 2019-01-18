package moura.renan.domain.usecase.features.notes

import io.reactivex.Completable
import moura.renan.domain.executor.PostExecutionThread
import moura.renan.domain.repository.NotesRepository
import moura.renan.domain.usecase.base.BaseCompletableUseCase

class DeleteAllNotes(
    private val notesRepository: NotesRepository,
    postExecutionThread: PostExecutionThread
) : BaseCompletableUseCase<Unit>(postExecutionThread) {


    override fun buildUseCaseCompletable(param: Unit?): Completable {
        return notesRepository.deleteAllNotes()
    }
}