package com.mn.githubrepository

import com.mn.githubrepository.api.GithubApi
import com.mn.githubrepository.data.DataStore
import com.mn.githubrepository.thread.ThreadTransformer
import com.mn.githubrepository.usecase.GetRepos

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

abstract class Repository {
    abstract fun getRepos(username: String, dataStore: DataStore): GetRepos

    companion object {
        fun getRepository(threadTransformer: ThreadTransformer): Repository {
            return RepositoryImpl(threadTransformer)
        }

        private class RepositoryImpl(private val threadTransformer: ThreadTransformer)
            : Repository() {
            private val retrofit: Retrofit = initRetrofit()
            private var githubApi: GithubApi = retrofit.create(GithubApi::class.java)

            private fun initRetrofit(): Retrofit {
                return Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }

            override fun getRepos(username: String, dataStore: DataStore): GetRepos {
                return GetRepos(githubApi, dataStore, username, threadTransformer)
            }
        }

    }
}
