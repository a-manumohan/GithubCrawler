package com.mn.githubrepository.usecase

import com.mn.githubrepository.api.GithubApi
import com.mn.githubrepository.data.DataStore
import com.mn.githubrepository.data.GithubRepo
import com.mn.githubrepository.thread.ThreadTransformer
import io.reactivex.Flowable
import io.reactivex.Single
import org.reactivestreams.Publisher
import org.reactivestreams.Subscriber

class GetRepos(private val githubApi: GithubApi,
               private val dataStore: DataStore,
               private val username: String,
               private val threadTransformer: ThreadTransformer) {

    fun fetch(page: Int, perPage: Int): Flowable<List<GithubRepo>> {
        val localFlowable = Flowable.fromIterable(dataStore.getItems())
                .toList()
                .toFlowable()

        val remoteFlowable = githubApi.getRepos(username, page, perPage)
                .compose(threadTransformer.apply())
                .doOnNext { repos: List<GithubRepo>? ->
                    persistToDataStore(repos)
                }
        return if (page == 0) {
            Flowable.concat(localFlowable, remoteFlowable)
        } else {
            remoteFlowable
        }
    }

    private fun persistToDataStore(repos: List<GithubRepo>?) {
        repos?.forEach { githubRepo ->
            dataStore.addItem(githubRepo)
        }
    }
}
