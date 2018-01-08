package com.mn.githubrepository.data

interface DataStore {
    fun addItem(item: GithubRepo)

    fun deleteItem(item: GithubRepo)

    fun getItems(): List<GithubRepo>
}