package moura.renan.domain.usecase.base

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import moura.renan.domain.executor.PostExecutionThread

abstract class BaseObservableUseCase<T, in Params> constructor(private val postExecutionThread: PostExecutionThread)  {

    private val disposables = CompositeDisposable()

    abstract fun buildUseCaseObservable(params: Params? = null) : Observable<T>

    open fun execute(observer : DisposableObserver<T>, params: Params? = null) {
        val observable = this.buildUseCaseObservable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.scheduler)

        addDisposable(observable.subscribeWith(observer))
    }


    fun dispose() {
        disposables.dispose()
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

}