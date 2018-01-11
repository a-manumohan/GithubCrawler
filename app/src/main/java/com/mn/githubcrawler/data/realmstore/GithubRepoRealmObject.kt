package com.mn.githubcrawler.data.realmstore

import com.mn.githubrepository.data.GithubRepo
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class GithubRepoRealmObject : RealmObject() {

    lateinit var name: String

    lateinit var fullName: String

    @PrimaryKey
    lateinit var url: String

    companion object {
        fun toGithubRepo(realmObject: GithubRepoRealmObject): GithubRepo {
            return GithubRepo(realmObject.name,
                    realmObject.fullName,
                    realmObject.url)
        }
    }
}