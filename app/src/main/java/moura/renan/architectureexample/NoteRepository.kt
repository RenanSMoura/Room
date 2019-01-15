package moura.renan.architectureexample

import android.app.Application
import io.reactivex.Completable
import io.reactivex.Observable
import moura.renan.architectureexample.dao.NoteDatabase
import moura.renan.architectureexample.model.Note
import moura.renan.architectureexample.repository.NotesCache

class NoteRepository(val application: Application) : NotesCache {

    val noteDao = NoteDatabase.getInstance(application)?.getNoteDao()

    override fun deleteAllNotes(): Completable {
        return Completable.defer {
            noteDao?.deleteAllNotes()
            Completable.complete()
        }
    }

    override fun saveNote(note: Note): Completable {
        return Completable.defer {
            noteDao?.insert(note)
            Completable.complete()
        }
    }

    override fun getNotes(): Observable<List<Note>>? {
        return noteDao?.getAllNotes()?.toObservable()
    }

    override fun updateNote(note: Note): Completable {
        return Completable.defer {
            noteDao?.update(note)
            Completable.complete()
        }
    }


}