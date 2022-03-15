package com.pedro.doggos.core.util

import io.reactivex.Scheduler

interface SchedulerProvider {

    val ioScheduler: Scheduler
    val uiScheduler: Scheduler
}