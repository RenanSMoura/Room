package moura.renan.data.dao

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import moura.renan.data.factory.NoteDataFactory
import moura.renan.data.mapper.NoteDataMapper
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class NoteDaoTest {
    @Rule
    @JvmField var instantTaskExecutorRule = InstantTaskExecutorRule()


    private val noteDataBase = Room.inMemoryDatabaseBuilder(
        RuntimeEnvironment.application.applicationContext
        , NoteDatabase::class.java
    ).allowMainThreadQueries().build()

    private val mapper = NoteDataMapper()
    @After
    fun closeDb() {
        noteDataBase.close()
    }

    @Test
    fun getNotesReturnData() {
        val note = mapper.mapToEntity(NoteDataFactory.createNote())
        noteDataBase.getNoteDao().insert(note)
        val testObserver = noteDataBase.getNoteDao().getAllNotes().test()
        testObserver.assertValue(listOf(note))

    }

    @Test
    fun deleteAllNotesReturnClearData() {
        val note = mapper.mapToEntity(NoteDataFactory.createNote())
        noteDataBase.getNoteDao().insert(note)
        noteDataBase.getNoteDao().deleteAllNotes()
        noteDataBase.getNoteDao().getAllNotes().test().assertValue(emptyList())
    }

    @Test
    fun deleteOneNoteReturnsClearData() {
        val note = mapper.mapToEntity(NoteDataFactory.createNote())
        noteDataBase.getNoteDao().insert(note)
        noteDataBase.getNoteDao().delet(note)
        noteDataBase.getNoteDao().getAllNotes().test().assertValue(emptyList())
    }


    //Tem que acertar essa porra depois
    @Test
    fun deleteOneNoteReturnsNotesData() {
        val notes = NoteDataFactory.createNoteList(3).map { mapper.mapToEntity(it) }.sortedWith(compareBy { it.id })
        notes.forEach {
            noteDataBase.getNoteDao().insert(it)
        }
        noteDataBase.getNoteDao().delet(notes.first())
        noteDataBase.getNoteDao().getAllNotes().test().assertValue(notes.drop(1))
    }

    @Test
    fun checkIfUpdatedNoteIsTheSame() {
        val note = mapper.mapToEntity(NoteDataFactory.createNote())
        noteDataBase.getNoteDao().insert(note)
        note.description =  "description"
        noteDataBase.getNoteDao().update(note)
        noteDataBase.getNoteDao().getAllNotes().test().assertValue(listOf(note))
    }
}