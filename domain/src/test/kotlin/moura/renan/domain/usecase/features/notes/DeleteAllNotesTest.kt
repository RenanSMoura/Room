package moura.renan.domain.usecase.features.notes

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import moura.renan.domain.executor.PostExecutionThread
import moura.renan.domain.repository.NotesRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
open class DeleteAllNotesTest {


    private lateinit var deleteAllNotes: DeleteAllNotes
    @Mock lateinit var  noteRepository : NotesRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        deleteAllNotes = DeleteAllNotes(noteRepository,postExecutionThread)
    }


    @Test
    fun deleteAllNotesComplete() {
        stubDeleteNotes(Completable.complete())
        deleteAllNotes.buildUseCaseCompletable().test().assertComplete()
    }

    @Test
    fun deleteAllNotesCallRepository(){
        stubDeleteNotes(Completable.complete())
        deleteAllNotes.buildUseCaseCompletable().test()
        verify(noteRepository).deleteAllNotes()
    }

    private fun stubDeleteNotes( completable: Completable) {
        whenever(noteRepository.deleteAllNotes()).thenReturn(completable)
    }
}