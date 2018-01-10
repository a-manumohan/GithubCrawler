package com.mn.githubrepository.thread

import io.reactivex.FlowableTransformer

interface ThreadTransformer {
    fun <T> apply(): FlowableTransformer<T, T>
}