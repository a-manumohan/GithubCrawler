package com.mn.githubcrawler.data.realmstore

import com.mn.githubrepository.data.GithubRepo
import io.realm.RealmObject

open class GithubRepoRealmObject(var name: String,
                            var fullName: String,
                            var url: String,
                            var language: String) : RealmObject() {
    companion object {
        fun toGithubRepo(realmObject: GithubRepoRealmObject): GithubRepo {
            return GithubRepo(realmObject.name,
                    realmObject.fullName,
                    realmObject.url,
                    realmObject.language)
        }
    }
}