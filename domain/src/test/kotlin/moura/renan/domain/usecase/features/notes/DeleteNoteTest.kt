package moura.renan.domain.usecase.features.notes

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import moura.renan.domain.executor.PostExecutionThread
import moura.renan.domain.model.NoteDomain
import moura.renan.domain.repository.NotesRepository
import moura.renan.domain.usecase.features.notes.factory.NoteDataFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.lang.UnsupportedOperationException


@RunWith(MockitoJUnitRunner::class)
class DeleteNoteTest {


    private lateinit var deleteNoteUseCase: DeleteNote
    @Mock
    lateinit var  noteRepository : NotesRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread



    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        deleteNoteUseCase = DeleteNote(noteRepository,postExecutionThread)
    }


    @Test
    fun deleteNoteComplete(){
        stubDeleteOneNote(Completable.complete())
        deleteNoteUseCase.buildUseCaseCompletable(
            NoteDataFactory.createNote()).test().assertComplete()
    }

    @Test(expected =  UnsupportedOperationException::class)
    fun callDeleteNoteWithNoParameterThrowsException(){
        deleteNoteUseCase.buildUseCaseCompletable().test()
    }

    @Test
    fun deleteNoteCompleteCallRepository(){
        val note = NoteDataFactory.createNote()
        stubDeleteOneNote(Completable.complete())
        deleteNoteUseCase.buildUseCaseCompletable(note).test()
        verify(noteRepository).deleteNote(note)
    }


    private fun stubDeleteOneNote(completable: Completable){
        whenever(noteRepository.deleteNote(any())).thenReturn(completable)
    }

}