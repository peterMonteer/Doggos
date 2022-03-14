package com.pedro.doggos.core.util

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppSchedulerProvider: SchedulerProvider {
    override val asyncTaskScheduler = Schedulers.io()
    override val uiScheduler: Scheduler = AndroidSchedulers.mainThread()
}