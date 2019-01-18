package moura.renan.domain.usecase.features.notes

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import moura.renan.domain.executor.PostExecutionThread
import moura.renan.domain.repository.NotesRepository
import moura.renan.domain.usecase.features.notes.factory.NoteDataFactory
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.lang.UnsupportedOperationException


@RunWith(MockitoJUnitRunner::class)
class SaveNoteTest {


    private lateinit var saveNoteUseCase : SaveNote

    @Mock lateinit var  noteRepository : NotesRepository

    @Mock lateinit var  postExecutionThread: PostExecutionThread



    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        saveNoteUseCase = SaveNote(noteRepository,postExecutionThread)
    }


    @Test
    fun saveNote() {
        val note = NoteDataFactory.createNote()
        stubSaveNote(Completable.complete())
        saveNoteUseCase.buildUseCaseCompletable(note).test().assertComplete()
    }


    @Test(expected =  UnsupportedOperationException::class)
    fun saveNoteThrowsExceptionWhenNoteisNull() {
        saveNoteUseCase.buildUseCaseCompletable().test().assertComplete()
    }


    private fun stubSaveNote(completable: Completable) {
        whenever(noteRepository.saveNote(any())).thenReturn(completable)
    }

}