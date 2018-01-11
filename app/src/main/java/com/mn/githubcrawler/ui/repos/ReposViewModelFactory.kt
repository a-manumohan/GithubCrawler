package com.mn.githubcrawler.ui.repos

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.mn.githubcrawler.ui.provider.StringProvider
import com.mn.githubrepository.Repository
import com.mn.githubrepository.data.DataStore
import javax.inject.Inject

class ReposViewModelFactory @Inject constructor(private val repository: Repository,
                                                private val dataStore: DataStore,
                                                private val stringProvider: StringProvider) :
        ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReposViewModel::class.java)) {
            return ReposViewModel(repository, dataStore, stringProvider) as T
        }
        throw IllegalArgumentException("Unknown view model")
    }
}