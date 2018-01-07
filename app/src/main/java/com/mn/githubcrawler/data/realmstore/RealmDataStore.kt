package com.mn.githubcrawler.data.realmstore

import com.mn.githubrepository.data.GithubRepo
import com.mn.githubrepository.data.DataStore
import io.realm.Realm

class RealmDataStore(private val realm: Realm) : DataStore {
    override fun addItem(item: GithubRepo) {
        realm.executeTransactionAsync {
            val githubRepoRealmObject = realm.createObject(GithubRepoRealmObject::class.java)
            githubRepoRealmObject.name = item.name
            githubRepoRealmObject.fullName = item.fullName
            githubRepoRealmObject.url = item.url
            githubRepoRealmObject.language = item.language
        }
    }

    override fun deleteItem(item: GithubRepo) {
        realm.executeTransaction {
            val results = realm.where(GithubRepoRealmObject::class.java)
                    .equalTo("fullName", item.fullName)
                    .and()
                    .equalTo("url", item.url)
                    .findAll()
            results.deleteAllFromRealm()
        }
    }

    override fun getItems(): List<GithubRepo> {
        return realm.where(GithubRepoRealmObject::class.java)
                .findAll()
                .map { item -> GithubRepoRealmObject.toGithubRepo(item) }
    }
}