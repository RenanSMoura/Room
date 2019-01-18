package moura.renan.domain.usecase.features.notes

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.Single
import moura.renan.domain.executor.PostExecutionThread
import moura.renan.domain.model.NoteDomain
import moura.renan.domain.repository.NotesRepository
import moura.renan.domain.usecase.features.notes.factory.DataFactory
import moura.renan.domain.usecase.features.notes.factory.NoteDataFactory
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GetNotesUseCaseTest {
    private lateinit var getNotesUseCase: GetNotesUseCase
    @Mock
    lateinit var  noteRepository : NotesRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getNotesUseCase = GetNotesUseCase(noteRepository,postExecutionThread)
    }

    @Test
    fun getNotesComplete(){
        stubGetNotesCallback(Single.just(listOf(NoteDataFactory.createNote())).toObservable())
        getNotesUseCase.buildUseCaseObservable().test().assertComplete()
    }

    @Test
    fun getNotesCallRepository(){
        val note = NoteDataFactory.createNote()
        stubGetNotesCallback(Single.just(listOf(note)).toObservable())
        getNotesUseCase.buildUseCaseObservable().test()
        verify(noteRepository).getNotes()
    }

    @Test
    fun getNoteReturnsData(){
        val note = NoteDataFactory.createNote()
        stubGetNotesCallback(Single.just(listOf(note)).toObservable())
        getNotesUseCase.buildUseCaseObservable().test().assertValue(listOf(note))
    }



    private fun stubGetNotesCallback(observer : Observable<List<NoteDomain>>){
        whenever(noteRepository.getNotes()).thenReturn(observer)
    }
}