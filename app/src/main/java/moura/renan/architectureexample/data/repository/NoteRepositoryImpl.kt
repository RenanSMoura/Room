package moura.renan.architectureexample.data.repository

import android.app.Application
import io.reactivex.Completable
import io.reactivex.Observable
import moura.renan.architectureexample.data.dao.NoteDatabase
import moura.renan.architectureexample.data.mapper.NoteDataMapper
import moura.renan.architectureexample.domain.model.NoteDomain
import moura.renan.architectureexample.domain.repository.NotesRepository

//Aqui eu consigo colocar o factory pattern para pegar de outros lugares diferentes

class NoteRepositoryImpl(application: Application, private val mapper: NoteDataMapper) : NotesRepository {

    //Ver maneira melhor de injetar esse cara?
    val noteDao = NoteDatabase.getInstance(application)?.getNoteDao()

    override fun deleteNote(note: NoteDomain): Completable {
        return Completable.defer {
            noteDao?.delet(mapper.mapToEntity(note))
            Completable.complete()
        }
    }

    override fun deleteAllNotes(): Completable {
        return Completable.defer {
            noteDao?.deleteAllNotes()
            Completable.complete()
        }
    }

    override fun saveNote(note: NoteDomain): Completable {
        return Completable.defer {
            noteDao?.insert(note.let { mapper.mapToEntity(it) })
            Completable.complete()
        }
    }

    override fun getNotes(): Observable<List<NoteDomain>> {
        return noteDao?.getAllNotes()?.toObservable()?.map { list -> list.map { mapper.mapFromEntity(it) } }!!
    }

    override fun updateNote(note: NoteDomain): Completable {
        return Completable.defer {
            noteDao?.update(mapper.mapToEntity(note))
            Completable.complete()
        }
    }
}