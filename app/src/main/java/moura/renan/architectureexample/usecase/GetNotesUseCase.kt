package moura.renan.architectureexample.usecase

import android.app.Application
import io.reactivex.Observable
import moura.renan.architectureexample.NoteRepository
import moura.renan.architectureexample.model.Note

class GetNotesUseCase (application : Application): BaseObservableUseCase<List<Note>,Note>() {

    private val noteRepository = NoteRepository(application)

    override fun buildUseCaseObservable(params: Note?): Observable<List<Note>> {
        return noteRepository.getNotes()!!
    }


}

