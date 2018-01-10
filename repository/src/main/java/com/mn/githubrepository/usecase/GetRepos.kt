package com.mn.githubrepository.usecase

import com.mn.githubrepository.api.GithubApi
import com.mn.githubrepository.data.DataStore
import com.mn.githubrepository.data.GithubRepo

import io.reactivex.Flowable
import org.reactivestreams.Publisher
import org.reactivestreams.Subscriber

class GetRepos(private val githubApi: GithubApi,
               private val dataStore: DataStore,
               private val username: String) {

    fun fetch(page: Int, perPage: Int): Flowable<List<GithubRepo>> {
        return githubApi.getRepos(username, page, perPage)
                .startWith(readFromLocalStore(page))
                .doOnNext { repos: List<GithubRepo>? ->
                    persistToDataStore(repos)
                }
    }

    private fun readFromLocalStore(page: Int): Publisher<List<GithubRepo>> {
        return Publisher { s: Subscriber<in List<GithubRepo>>? ->
            //read from local store only if the page is 0
            if (page == 0) {
                s?.onNext(dataStore.getItems())
                s?.onComplete()
            } else {
                s?.onComplete()
            }
        }
    }

    private fun persistToDataStore(repos: List<GithubRepo>?) {
        repos?.forEach { githubRepo ->
            dataStore.addItem(githubRepo)
        }
    }
}
