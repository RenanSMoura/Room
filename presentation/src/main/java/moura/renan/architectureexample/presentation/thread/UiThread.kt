package moura.renan.architectureexample.presentation.thread

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import moura.renan.domain.executor.PostExecutionThread

class UiThread : PostExecutionThread {
    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}