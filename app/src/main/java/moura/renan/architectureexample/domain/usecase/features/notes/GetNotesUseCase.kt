package moura.renan.architectureexample.domain.usecase.features.notes

import io.reactivex.Observable
import moura.renan.architectureexample.domain.model.NoteDomain
import moura.renan.architectureexample.domain.repository.NotesRepository
import moura.renan.architectureexample.domain.usecase.base.BaseObservableUseCase

class GetNotesUseCase (private val notesRepository: NotesRepository): BaseObservableUseCase<List<NoteDomain>, Unit>() {

    override fun buildUseCaseObservable(params: Unit?): Observable<List<NoteDomain>> {
        return notesRepository.getNotes()
    }
}

