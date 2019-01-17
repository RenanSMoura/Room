package moura.renan.domain.repository

import io.reactivex.Completable
import io.reactivex.Observable
import moura.renan.domain.model.NoteDomain

interface NotesRepository {

    fun deleteAllNotes() : Completable

    fun saveNote(note : NoteDomain) : Completable

    fun getNotes() : Observable<List<NoteDomain>>

    fun updateNote(note : NoteDomain) : Completable

    fun deleteNote(note : NoteDomain) : Completable
}

