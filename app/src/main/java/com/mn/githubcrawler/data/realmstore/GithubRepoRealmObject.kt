package com.mn.githubcrawler.data.realmstore

import com.mn.githubrepository.data.GithubRepo
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class GithubRepoRealmObject() : RealmObject() {

    lateinit var name: String

    lateinit var fullName: String

    @PrimaryKey
    lateinit var url: String

    lateinit var language: String

    constructor(name: String,
                fullName: String,
                url: String,
                language: String) : this() {
        this.name = name
        this.fullName = fullName
        this.url = url
        this.language = language
    }

    companion object {
        fun toGithubRepo(realmObject: GithubRepoRealmObject): GithubRepo {
            return GithubRepo(realmObject.name,
                    realmObject.fullName,
                    realmObject.url,
                    realmObject.language)
        }
    }
}