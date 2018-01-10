package com.mn.githubcrawler.application

import com.mn.githubcrawler.ui.repos.ReposActivity
import com.mn.githubcrawler.ui.repos.ReposModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [ReposModule::class])
    abstract fun bindReposActivity() : ReposActivity
}