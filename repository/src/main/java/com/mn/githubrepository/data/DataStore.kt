package com.mn.githubrepository.data

interface DataStore<T> {
    fun addItem(item : T)

    fun deleteItem(item: T)

    fun getItems(): List<T>
}