package com.mn.githubcrawler.ui.repos

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.mn.githubcrawler.R
import com.mn.githubcrawler.ui.provider.StringProvider
import com.mn.githubcrawler.ui.repos.model.RepoUiModel
import com.mn.githubcrawler.ui.repos.model.ReposUiModel
import com.mn.githubrepository.Repository
import com.mn.githubrepository.data.DataStore
import com.mn.githubrepository.data.GithubRepo
import io.reactivex.Flowable.fromIterable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class ReposViewModel(private val repository: Repository,
                     private val dataStore: DataStore,
                     private val stringProvider: StringProvider) : ViewModel() {
    companion object {
        const val USERNAME = "JakeWharton"
        const val PER_PAGE = 15
    }

    private val reposMutableLiveData = MutableLiveData<ReposUiModel>()
    private val errorsMutableLiveData = MutableLiveData<String>()
    private val compositeDisposable = CompositeDisposable()
    private val githubRepos = mutableListOf<GithubRepo>()
    private var currentPage = 1 //page starts from 1
    private var loadMore = true

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun reposLiveData(): LiveData<ReposUiModel> = reposMutableLiveData
    fun errorsLiveData(): LiveData<String> = errorsMutableLiveData

    fun onLoad() {
        if (githubRepos.isEmpty()) {
            fetchFirstPage()
        } else {
            showFirstPage(githubRepos)
        }
    }

    private fun fetchFirstPage() {
        compositeDisposable.add(repository.getRepos(USERNAME, dataStore)
                .fetch(1, PER_PAGE)
                .subscribe({ githubRepos ->
                    this.githubRepos.addAll(githubRepos)
                    showFirstPage(githubRepos)
                },
                        { t -> handleLoadError(t) }))
    }

    private fun fetchPage(page: Int, perPage: Int) {
        compositeDisposable.add(repository.getRepos(USERNAME, dataStore)
                .fetch(page, perPage)
                .subscribe({ githubRepos ->
                    updateLoadMore(githubRepos.size)
                    this.githubRepos.addAll(githubRepos)
                    updatePage(githubRepos)
                }, { t ->
                    handleLoadError(t)
                }))
    }

    private fun handleLoadError(t: Throwable) {
        loadMore = false
        errorsMutableLiveData.value = stringProvider.getString(R.string.error_message)
    }

    private fun loadNextPage() {
        if (loadMore) {
            currentPage++
            fetchPage(currentPage, PER_PAGE)
        }
    }

    private fun showFirstPage(githubRepos: List<GithubRepo>) {
        compositeDisposable.add(mapToRepoUiModel(githubRepos)
                .map { repoUiModels ->
                    ReposUiModel(repoUiModels, false, {
                        loadNextPage()
                    })
                }
                .subscribe { reposUiModel -> reposMutableLiveData.value = reposUiModel }
        )
    }

    private fun updatePage(githubRepos: List<GithubRepo>) {
        compositeDisposable.add(mapToRepoUiModel(githubRepos)
                .map { repoUiModels ->
                    ReposUiModel(repoUiModels, true, {
                        loadNextPage()
                    })
                }
                .subscribe { reposUiModel -> reposMutableLiveData.value = reposUiModel }
        )
    }

    private fun mapToRepoUiModel(githubRepos: List<GithubRepo>): Single<List<RepoUiModel>> {
        return fromIterable(githubRepos)
                .map { githubRepo -> RepoUiModel(githubRepo.name, githubRepo.url) }
                .toList()
    }

    private fun updateLoadMore(size: Int) {
        loadMore = size >= 15
    }
}