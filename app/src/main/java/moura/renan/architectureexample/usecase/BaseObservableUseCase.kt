package moura.renan.architectureexample.usecase

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class BaseObservableUseCase<T, in Params>  {

    private val disposables = CompositeDisposable()

    abstract fun buildUseCaseObservable(params: Params? = null) : Observable<T>

    open fun execute(observer : DisposableObserver<T>, params: Params? = null) {
        val observable = this.buildUseCaseObservable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        addDisposable(observable.subscribeWith(observer))
    }


    fun dispose() {
        disposables.dispose()
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

}