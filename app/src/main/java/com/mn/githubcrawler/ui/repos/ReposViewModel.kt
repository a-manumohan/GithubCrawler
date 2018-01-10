package com.mn.githubcrawler.ui.repos

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.mn.githubcrawler.ui.repos.model.ReposUiModel
import com.mn.githubrepository.Repository
import com.mn.githubrepository.data.DataStore

class ReposViewModel(private val repository: Repository,
                     private val dataStore: DataStore) : ViewModel() {
    private val reposMutableLiveData = MutableLiveData<ReposUiModel>()

    fun reposLiveData(): LiveData<ReposUiModel> = reposMutableLiveData
    fun onLoad() {
        repository.getRepos("manu", dataStore)
    }
}