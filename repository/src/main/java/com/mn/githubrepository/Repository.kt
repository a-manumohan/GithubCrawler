package com.mn.githubrepository

import com.mn.githubrepository.api.GithubApi
import com.mn.githubrepository.data.DataStore
import com.mn.githubrepository.data.GithubRepo
import com.mn.githubrepository.usecase.GetRepos

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Repository {
    private val retrofit: Retrofit = initRetrofit()
    private var githubApi: GithubApi = retrofit.create(GithubApi::class.java)

    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun getRepos(username: String, dataStore: DataStore): GetRepos {
        return GetRepos(githubApi, dataStore, username)
    }
}
