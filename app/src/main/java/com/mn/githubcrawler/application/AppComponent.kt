package com.mn.githubcrawler.application

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityModule::class])
@Singleton
interface AppComponent : AndroidInjector<GithubCrawlerApplication> {
    override fun inject(githubCrawlerApplication: GithubCrawlerApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: GithubCrawlerApplication) : Builder

        fun build(): AppComponent
    }
}
