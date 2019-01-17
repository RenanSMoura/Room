package moura.renan.architectureexample.presentation.view.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import moura.renan.architectureexample.domain.model.NoteDomain
import moura.renan.architectureexample.domain.usecase.features.notes.DeleteAllNotes
import moura.renan.architectureexample.presentation.state.Resource
import moura.renan.architectureexample.presentation.state.ResourceState
import moura.renan.architectureexample.domain.usecase.features.notes.GetNotesUseCase
import moura.renan.architectureexample.presentation.mapper.NoteViewMapper
import moura.renan.architectureexample.presentation.model.NoteView

open class NoteViewModel constructor(
    private val deleteAllNotesUseCase : DeleteAllNotes,
    private val getNotesUseCase: GetNotesUseCase,
    private val mapper: NoteViewMapper) : ViewModel() {

    private val noteLiveData = MutableLiveData<Resource<List<NoteView>>>()

    override fun onCleared() {
        getNotesUseCase.dispose()
        super.onCleared()
    }

    fun getNotes() : LiveData<Resource<List<NoteView>>> {
        return noteLiveData
    }

    fun fetchNotes(){
        noteLiveData.postValue(Resource(ResourceState.LOADING))
        return getNotesUseCase.execute(object : DisposableObserver<List<NoteDomain>>(){
            override fun onComplete() {}

            override fun onNext(list: List<NoteDomain>) {
                noteLiveData.postValue(
                    Resource(
                        ResourceState.SUCCESS,
                        list.map { mapper.mapToEntity(it) }
                    )
                )
            }

            override fun onError(e: Throwable) {
                noteLiveData.postValue(
                    Resource(
                        ResourceState.ERROR,
                        message = e.localizedMessage
                    )
                )
            }

        })
    }


    fun deleteAllNotes() {
        noteLiveData.postValue(Resource(ResourceState.LOADING))
        return deleteAllNotesUseCase.execute(object : DisposableCompletableObserver(){
            override fun onComplete() {
                noteLiveData.postValue(Resource(ResourceState.SUCCESS, noteLiveData.value?.data))
            }

            override fun onError(e: Throwable) {
                noteLiveData.postValue(
                    Resource(
                        ResourceState.ERROR,
                        message = e.localizedMessage
                    )
                )
            }

        })
    }





}