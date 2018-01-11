package com.mn.githubcrawler.data.realmstore

import com.mn.githubrepository.data.GithubRepo
import com.mn.githubrepository.data.DataStore
import io.realm.Realm

class RealmDataStore : DataStore {

    override fun addItem(item: GithubRepo) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            var githubRepoRealmObject = realm.where(GithubRepoRealmObject::class.java)
                    .equalTo("url", item.url)
                    .findFirst()
            if (githubRepoRealmObject == null) {
                githubRepoRealmObject = realm.createObject(GithubRepoRealmObject::class.java, item.url)
            }
            githubRepoRealmObject?.name = item.name
            githubRepoRealmObject?.fullName = item.fullName
            githubRepoRealmObject?.language = item.language
        }
    }

    override fun deleteItem(item: GithubRepo) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            val results = realm.where(GithubRepoRealmObject::class.java)
                    .equalTo("url", item.url)
                    .findAll()
            results.deleteAllFromRealm()
        }
    }

    override fun getItems(): List<GithubRepo> {
        val realm = Realm.getDefaultInstance()
        return realm.where(GithubRepoRealmObject::class.java)
                .findAll()
                .map { item -> GithubRepoRealmObject.toGithubRepo(item) }
    }
}