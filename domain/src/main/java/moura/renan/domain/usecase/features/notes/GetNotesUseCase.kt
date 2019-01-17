package moura.renan.domain.usecase.features.notes

import io.reactivex.Observable
import moura.renan.domain.executor.PostExecutionThread
import moura.renan.domain.model.NoteDomain
import moura.renan.domain.repository.NotesRepository
import moura.renan.domain.usecase.base.BaseObservableUseCase

class GetNotesUseCase (private val notesRepository: NotesRepository,
                       postExecutionThread: PostExecutionThread): BaseObservableUseCase<List<NoteDomain>, Unit>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Unit?): Observable<List<NoteDomain>> {
        return notesRepository.getNotes()
    }
}

