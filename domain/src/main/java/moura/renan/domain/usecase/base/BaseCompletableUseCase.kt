package moura.renan.domain.usecase.base

import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import moura.renan.domain.executor.PostExecutionThread

abstract class BaseCompletableUseCase<in Params> constructor(private val postExecutionThread: PostExecutionThread){

    private val disposables = CompositeDisposable()

    abstract fun buildUseCaseCompletable(param: Params? = null): Completable

    open fun execute(observer: DisposableCompletableObserver, param: Params? = null) {
        val observable = this.buildUseCaseCompletable(param).subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.scheduler)
        addDisposable(observable.subscribeWith(observer))
    }

    fun dispose() {
        disposables.dispose()
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.addAll(disposable)
    }
}