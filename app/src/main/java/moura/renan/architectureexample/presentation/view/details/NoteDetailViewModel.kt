package moura.renan.architectureexample.presentation.view.details

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.observers.DisposableCompletableObserver
import moura.renan.architectureexample.domain.usecase.features.notes.UpdateNote
import moura.renan.architectureexample.presentation.mapper.NoteViewMapper
import moura.renan.architectureexample.presentation.model.NoteView
import moura.renan.architectureexample.presentation.state.Resource
import moura.renan.architectureexample.presentation.state.ResourceState

class NoteDetailViewModel constructor(private val updateNotesUseCase: UpdateNote,
                                      private val mapper: NoteViewMapper) : ViewModel() {


    private val liveData = MutableLiveData<Resource<Nothing>>()

    override fun onCleared() {
        updateNotesUseCase.dispose()
        super.onCleared()
    }


    fun observeLiveData() : MutableLiveData<Resource<Nothing>> {
        return liveData
    }

    fun updateNote(noteView: NoteView) {
        liveData.postValue(Resource(ResourceState.LOADING))

        updateNotesUseCase.execute(object : DisposableCompletableObserver() {
            override fun onComplete() {
                liveData.postValue(Resource(ResourceState.SUCCESS,liveData.value?.data))
            }

            override fun onError(e: Throwable) {
                liveData.postValue(
                    Resource(
                        ResourceState.ERROR,
                        message = e.localizedMessage
                    )
                )
            }

        },mapper.mapFromEntity(noteView))
    }

}