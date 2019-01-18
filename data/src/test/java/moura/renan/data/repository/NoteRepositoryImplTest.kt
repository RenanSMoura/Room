package moura.renan.data.repository

import android.app.Instrumentation
import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import moura.renan.data.dao.NoteDatabase
import moura.renan.data.factory.NoteDataFactory
import moura.renan.data.mapper.NoteDataMapper
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class NoteRepositoryImplTest {


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val noteDataBase = Room.inMemoryDatabaseBuilder(
        RuntimeEnvironment.application.applicationContext
        , NoteDatabase::class.java
    ).allowMainThreadQueries().build()

    private val mapper = NoteDataMapper()
    private val cache = NoteRepositoryImpl(noteDataBase, mapper)


    @After
    fun closesDb() {
        noteDataBase.close()
    }


    @Test
    fun getAllNotesComplete() {
        val notes = NoteDataFactory.createNote()

        cache.saveNote(notes).test()

        cache.getNotes().test().assertComplete()
    }

    @Test
    fun getAllNotesReturnData() {
        val note = NoteDataFactory.createNote()
        cache.saveNote(note).test()
        val testObserver = cache.getNotes().test()
        testObserver.assertValue(listOf(note))
    }

    @Test
    fun clearNoteTableCompletes() {
        cache.deleteAllNotes().test().assertComplete()
    }

    @Test
    fun updateNoteCompletes() {
        val note = NoteDataFactory.createNote()
        cache.saveNote(note).test()
        cache.updateNote(note).test().assertComplete()
    }

    @Test
    fun deleteOneNoteCompletes() {
        val notes = NoteDataFactory.createNote()
        cache.saveNote(notes).test()
        cache.deleteNote(notes).test().assertComplete()
    }


}