package com.mn.githubcrawler.data.realmstore

import com.mn.githubrepository.data.GithubRepo
import io.realm.RealmObject

open class GithubRepoRealmObject(val name: String,
                            val fullName: String,
                            val url: String,
                            val language: String) : RealmObject() {
    companion object {
        fun from(githubRepo: GithubRepo): GithubRepoRealmObject {
            return GithubRepoRealmObject(githubRepo.name,
                    githubRepo.fullName,
                    githubRepo.url,
                    githubRepo.language)
        }

        fun toGithubRepo(realmObject: GithubRepoRealmObject): GithubRepo {
            return GithubRepo(realmObject.name,
                    realmObject.fullName,
                    realmObject.url,
                    realmObject.language)
        }
    }
}