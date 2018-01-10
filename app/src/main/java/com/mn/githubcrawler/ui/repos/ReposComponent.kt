package com.mn.githubcrawler.ui.repos

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [ReposModule::class])
interface ReposComponent : AndroidInjector<ReposActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<ReposActivity>()
}