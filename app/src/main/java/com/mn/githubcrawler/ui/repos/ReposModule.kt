package com.mn.githubcrawler.ui.repos

import android.arch.lifecycle.ViewModelProviders
import com.mn.githubrepository.Repository
import dagger.Module
import dagger.Provides

@Module
class ReposModule {
    @Provides
    fun reposViewModel(activity: ReposActivity, reposViewModelFactory: ReposViewModelFactory)
            : ReposViewModel {
        return ViewModelProviders.of(activity, reposViewModelFactory).get(ReposViewModel::class.java)
    }

    @Provides
    fun reposViewModelFactory(repository: Repository): ReposViewModelFactory {
        return ReposViewModelFactory(repository)
    }
}
