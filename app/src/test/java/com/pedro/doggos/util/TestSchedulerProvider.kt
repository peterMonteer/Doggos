package com.pedro.doggos.util

import com.pedro.doggos.core.util.SchedulerProvider
import io.reactivex.schedulers.TestScheduler

class TestSchedulerProvider: SchedulerProvider {

    val testScheduler: TestScheduler = TestScheduler()

    override val asyncTaskScheduler = testScheduler
    override val uiScheduler = testScheduler
}