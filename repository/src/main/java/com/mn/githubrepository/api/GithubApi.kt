package com.mn.githubrepository.api


import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("users/{user}/repos")
    fun getRepos(@Path("user") username: String,
                 @Query("page") page: Int,
                 @Query("per_page") perPage: Int): Flowable<Any>
}
