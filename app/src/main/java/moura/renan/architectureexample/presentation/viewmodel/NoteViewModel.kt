package moura.renan.architectureexample.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.observers.DisposableObserver
import moura.renan.architectureexample.model.Note
import moura.renan.architectureexample.presentation.Resource
import moura.renan.architectureexample.presentation.ResourceState
import moura.renan.architectureexample.usecase.GetNotesUseCase

open class NoteViewModel : ViewModel() {

    private val noteLiveData = MutableLiveData<Resource<List<Note>>>()
    private lateinit var getNoteUseCase : GetNotesUseCase
    private lateinit var application : Application


    fun setApplication(application: Application) {
        this.application = application
        getNoteUseCase = GetNotesUseCase(this.application)
    }

    override fun onCleared() {
        getNoteUseCase.dispose()
        super.onCleared()
    }

    fun getNotes() : LiveData<Resource<List<Note>>> {
        return noteLiveData
    }

    fun fetchNotes(){
        noteLiveData.postValue(Resource(ResourceState.LOADING))
        return getNoteUseCase.execute(object : DisposableObserver<List<Note>>(){
            override fun onComplete() {}

            override fun onNext(list: List<Note>) {
                noteLiveData.postValue(Resource(ResourceState.SUCCESS,list))
            }

            override fun onError(e: Throwable) {
                noteLiveData.postValue(Resource(ResourceState.ERROR, message = e.localizedMessage))
            }

        })
    }





}