package com.mn.githubcrawler.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.mn.githubcrawler.application.GithubCrawlerApplication

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        (application as GithubCrawlerApplication).activityInjector.inject(this)
    }
}