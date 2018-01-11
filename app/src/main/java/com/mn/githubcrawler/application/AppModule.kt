package com.mn.githubcrawler.application

import com.mn.githubcrawler.data.realmstore.RealmDataStore
import com.mn.githubcrawler.thread.GcThreadTransformer
import com.mn.githubcrawler.thread.SchedulerProvider
import com.mn.githubrepository.Repository
import com.mn.githubrepository.data.DataStore
import com.mn.githubrepository.thread.ThreadTransformer
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideDataStore(): DataStore = RealmDataStore()

    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider {
        return object : SchedulerProvider {
            override fun io(): Scheduler {
                return Schedulers.io()
            }

            override fun main(): Scheduler {
                return AndroidSchedulers.mainThread()
            }

        }
    }

    @Provides
    @Singleton
    fun provideThreadTransformer(schedulerProvider: SchedulerProvider): ThreadTransformer {
        return GcThreadTransformer(schedulerProvider)
    }

    @Provides
    @Singleton
    fun provideRepository(threadTransformer: ThreadTransformer): Repository {
        return Repository(threadTransformer)
    }

}