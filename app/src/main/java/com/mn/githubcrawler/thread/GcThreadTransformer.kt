package com.mn.githubcrawler.thread

import com.mn.githubrepository.thread.ThreadTransformer
import io.reactivex.FlowableTransformer

class GcThreadTransformer(private val schedulerProvider: SchedulerProvider) : ThreadTransformer {
    override fun <T> apply(): FlowableTransformer<T, T> {
        return FlowableTransformer { o ->
            o.subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.main())
        }
    }
}