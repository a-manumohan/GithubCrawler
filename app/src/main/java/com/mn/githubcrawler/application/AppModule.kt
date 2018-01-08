package com.mn.githubcrawler.application

import android.content.Context
import com.mn.githubcrawler.data.realmstore.RealmDataStore
import com.mn.githubrepository.Repository
import com.mn.githubrepository.data.DataStore
import dagger.Module
import dagger.Provides
import io.realm.Realm
import javax.inject.Singleton

@Module
class AppModule(val context: Context) {

    @Provides
    @Singleton
    fun provideRealm() : Realm = Realm.getDefaultInstance()

    @Provides
    @Singleton
    fun provideDataStore(realm: Realm) : DataStore = RealmDataStore(realm)

    @Provides
    @Singleton
    fun provideRepository() : Repository = Repository()
}