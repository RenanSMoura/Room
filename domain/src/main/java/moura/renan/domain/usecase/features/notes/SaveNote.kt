package moura.renan.domain.usecase.features.notes

import io.reactivex.Completable
import moura.renan.domain.executor.PostExecutionThread
import moura.renan.domain.model.NoteDomain
import moura.renan.domain.repository.NotesRepository
import moura.renan.domain.usecase.base.BaseCompletableUseCase
import java.lang.UnsupportedOperationException

class SaveNote(
    private val notesRepository: NotesRepository,
    postExecutionThread: PostExecutionThread
) :
    BaseCompletableUseCase<NoteDomain>(postExecutionThread) {
    override fun buildUseCaseCompletable(param: NoteDomain?): Completable {
        if (param == null) throw  UnsupportedOperationException("Note cant be null")
        return notesRepository.saveNote(param)
    }
}