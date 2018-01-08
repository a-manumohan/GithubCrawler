package com.mn.githubrepository.usecase

import com.mn.githubrepository.api.GithubApi
import com.mn.githubrepository.data.DataStore
import com.mn.githubrepository.data.GithubRepo

import io.reactivex.Flowable

class GetRepos(private val githubApi: GithubApi,
               private val dataStore: DataStore,
               private val username: String) {

    fun fetch(page: Int, perPage: Int): Flowable<List<GithubRepo>> {
        //todo persist locally.
        //todo if the call fails and if the page is 0, read from local store.
        return githubApi.getRepos(username, page, perPage)
                .doOnNext { repos: List<GithubRepo>? ->
                    persistToDataStore(repos)
                }
    }

    private fun persistToDataStore(repos: List<GithubRepo>?) {
        repos?.forEach { githubRepo ->
            dataStore.addItem(githubRepo)
        }
    }
}
