package moura.renan.architectureexample.repository

import io.reactivex.Completable
import io.reactivex.Observable
import moura.renan.architectureexample.model.Note

interface NotesCache {

    fun deleteAllNotes() : Completable

    fun saveNote(note : Note) : Completable

    fun getNotes() : Observable<List<Note>>?

    fun updateNote(note : Note) : Completable
}

