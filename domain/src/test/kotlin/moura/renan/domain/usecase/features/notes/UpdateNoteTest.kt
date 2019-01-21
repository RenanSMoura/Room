package moura.renan.domain.usecase.features.notes

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
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
import java.lang.IllegalArgumentException
import java.lang.UnsupportedOperationException

@RunWith(MockitoJUnitRunner::class)
class UpdateNoteTest {


    private lateinit var updateNoteUsecase: UpdateNote

    @Mock
    lateinit var noteRepository: NotesRepository

    @Mock
    lateinit var postExecutionThread: PostExecutionThread


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        updateNoteUsecase = UpdateNote(noteRepository,postExecutionThread)
    }


    @Test
    fun updateNoteComplete() {
        stubUpdateNote(Completable.complete())
        updateNoteUsecase.buildUseCaseCompletable(NoteDataFactory.createNote()).test().assertComplete()

    }

    @Test
    fun updateNoteCallsRespository() {
        val note = NoteDataFactory.createNote()
        stubUpdateNote(Completable.complete())
        updateNoteUsecase.buildUseCaseCompletable(note).test()
        verify(noteRepository).updateNote(note)
    }


    @Test(expected = UnsupportedOperationException::class)
    fun callUpdateNoteWithNoParameterThrowsException(){
        updateNoteUsecase.buildUseCaseCompletable().test()
    }


    private fun stubUpdateNote(completable: Completable) {
        whenever(noteRepository.updateNote(any())).thenReturn(completable)
    }
}


