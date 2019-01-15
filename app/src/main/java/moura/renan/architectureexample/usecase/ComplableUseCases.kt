package moura.renan.architectureexample.usecase

import android.app.Application
import io.reactivex.Completable
import moura.renan.architectureexample.NoteRepository
import moura.renan.architectureexample.model.Note


class DeleteAllNotesUseCase(application: Application) : BaseCompletableUseCase<Note>() {
    private val noteRepository = NoteRepository(application)
    override fun buildUseCaseCompletable(param: Note?): Completable {
        return noteRepository.deleteAllNotes()
    }
}

class SaveNoteUseCase(application: Application) : BaseCompletableUseCase<Note>() {
    private val noteRepository = NoteRepository(application)
    override fun buildUseCaseCompletable(param: Note?): Completable {
        if (param == null) throw UnsupportedOperationException("Param cant be null")
        return noteRepository.saveNote(param)
    }
}

class UpdateNoteUseCase(application: Application) : BaseCompletableUseCase<Note>() {
    private val noteRepository = NoteRepository(application)
    override fun buildUseCaseCompletable(param: Note?): Completable {
        if (param == null) throw  java.lang.UnsupportedOperationException("Param cant be null")
        return noteRepository.updateNote(param)
    }

}


