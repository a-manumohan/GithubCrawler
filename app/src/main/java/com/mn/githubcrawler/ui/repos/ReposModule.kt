package com.mn.githubcrawler.ui.repos

import android.arch.lifecycle.ViewModelProviders
import com.mn.githubcrawler.ui.provider.StringProvider
import com.mn.githubcrawler.ui.provider.StringProviderImpl
import com.mn.githubrepository.Repository
import com.mn.githubrepository.data.DataStore
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
    fun reposViewModelFactory(repository: Repository,
                              dataStore: DataStore,
                              stringProvider: StringProvider): ReposViewModelFactory {
        return ReposViewModelFactory(repository, dataStore, stringProvider)
    }

    @Provides
    fun stringProvider(activity: ReposActivity): StringProvider {
        return StringProviderImpl(activity)
    }
}
