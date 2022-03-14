package com.pedro.doggos.core.util

import io.reactivex.Scheduler

interface SchedulerProvider {

    val asyncTaskScheduler: Scheduler
    val uiScheduler: Scheduler
}