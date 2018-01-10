package com.mn.githubcrawler.ui.repos

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.mn.githubrepository.Repository
import javax.inject.Inject

class ReposViewModelFactory @Inject constructor(private val repository: Repository) :
        ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReposViewModel::class.java)) {
            return ReposViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown view model")
    }
}