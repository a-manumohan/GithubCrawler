package com.mn.githubcrawler.ui.repos

import android.arch.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides

@Module
class ReposModule {
    @Provides
    fun reposViewModel(activity: ReposActivity): ReposViewModel {
        return ViewModelProviders.of(activity).get(ReposViewModel::class.java)
    }
}