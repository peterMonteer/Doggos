package com.pedro.doggos.util

import com.pedro.doggos.core.util.SchedulerProvider
import io.reactivex.schedulers.TestScheduler

class TestSchedulerProvider: SchedulerProvider {

    val testScheduler: TestScheduler = TestScheduler()

    override val ioScheduler = testScheduler
    override val uiScheduler = testScheduler
}