package moura.renan.architectureexample.usecase

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

abstract class BaseCompletableUseCase<in Params> {

    private val disposables = CompositeDisposable()

    abstract fun buildUseCaseCompletable(param: Params? = null): Completable

    open fun execute(observer: DisposableCompletableObserver, param: Params? = null) {
        val observable = this.buildUseCaseCompletable(param).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        addDisposable(observable.subscribeWith(observer))
    }

    fun dispose() {
        disposables.dispose()
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.addAll(disposable)
    }
}